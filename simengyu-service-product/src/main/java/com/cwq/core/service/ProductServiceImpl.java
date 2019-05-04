package com.cwq.core.service;


import com.cwq.core.dao.ColorDao;
import com.cwq.core.dao.ProductDao;
import com.cwq.core.dao.SkuDao;
import com.cwq.core.pojo.Color;
import com.cwq.core.pojo.Product;
import com.cwq.core.pojo.Sku;
import com.cwq.core.pojo.SuperPojo;
import com.cwq.core.tools.PageHelper;
import com.cwq.core.tools.PageHelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service(value = "productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ColorDao colorDao;

    @Autowired
    private SkuDao skuDao;

    @Autowired
    private Jedis jedis;

//    @Autowired
//    private HttpSolrServer solrServer;

    @Autowired
    private JmsTemplate jmsTemplate;

    /**
     * 根据条件查询带分页
     *
     * @param product  查询条件的模板对象
     * @param pageNum  当前页码
     * @param pageSize 每页显示行数
     * @return
     */
    public Page<Product> findProductPageByExample(Product product, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        if (product.getName() == null) {
            product.setName("");
        }

        productDao.findProductPage(product);

//       Example example = new Example(Product.class);
//       if(product.getBrandId() == null && product.getIsShow()==null){
//           example.createCriteria().andLike("name", "%"+product.getName()+"%");
//       }else if(product.getBrandId()!=null && product.getIsShow()==null){
//           example.createCriteria().andLike("name", "%"+product.getName()+"%").
//           andEqualTo("brandId", product.getBrandId());
//       }else if(product.getBrandId() == null && product.getIsShow()!=null){
//           example.createCriteria().andLike("name", "%"+product.getName()+"%").
//           andEqualTo("isShow", product.getIsShow());
//       }else{
//           example.createCriteria().andLike("name", "%"+product.getName()+"%").
//           andEqualTo("brandId", product.getBrandId()).
//           andEqualTo("isShow", product.getIsShow());
//       }
//       //按创建时间倒叙排序
//       example.setOrderByClause("createTime desc");
//       productDao.selectByExample(example);

        Page<Product> endPage = PageHelper.endPage();

        return endPage;
    }

    /**
     * 查询所有可用颜色
     *
     * @return
     */
    public List<Color> findEnableColors() {
//        Example example = new Example(Color.class);
//        example.createCriteria().andNotEqualTo("parentId", 0);
//        List<Color> colors = colorDao.selectByExample(example);
        List<Color> colors = colorDao.findEnableColors();
        return colors;
    }

    /**
     * 添加商品的时候添加库存
     *
     * @param product
     */
    public void addProductInfo(Product product) {

        //设置默认值
        if (product.getIsShow() == null) {
            product.setIsShow(0);
        }
        if (product.getCreateTime() == null) {
            product.setCreateTime(new Date());
        }
        //使用redis获得商品自动增长的id
        Long pnoid = jedis.incr("pno");
        product.setId(pnoid);

        //先添加商品到库中
//        productDao.insertSelective(product);
        productDao.insertProduct(product);
        System.out.println("回显商品id：" + product.getId());

        //将商品信息添加到库存表中
        //遍历不同的颜色和尺码 

        String[] colors = product.getColors().split(",");
        String[] sizes = product.getSizes().split(",");

        //每一个不同颜色或不同尺寸都应插入库存表，成为一条数据
        for (String color : colors) {
            for (String size : sizes) {
                //将商品信息存入库存
                Sku sku = new Sku();
                sku.setProductId(product.getId());
                sku.setColorId(Long.parseLong(color));
                sku.setSize(size);
                sku.setMarketPrice(130000f);
                sku.setPrice(11000f);
                sku.setDeliveFee(10f);
                sku.setStock(0);
                sku.setUpperLimit(3);
                sku.setCreateTime(new Date());
//                skuDao.insertSelective(sku);
                skuDao.insertSku(sku);

            }

        }


    }

    @Override
    public void singleDelete(Long productId) {

//        productDao.deleteByPrimaryKey(productId);
        productDao.deleteSingleProductById(productId);

    }

    @Override
    public void batchDelete(String ids) {

        String[] arrid = ids.split(",");

        List<String> listIds = Stream.of(arrid).collect(Collectors.toList());
//        Example example = new Example(Product.class);
//        example.createCriteria().andIn("id", listIds);
//        productDao.deleteByExample(example);
        productDao.deleteBatchProductByIds(listIds);

    }

    /**
     * 根据ids和标识完成商品上下架
     *
     * @param product
     * @param ids
     */
    public void isShowOrHide(Product product, String ids) {
        //将字符串数组转list
        String[] strIdsArr = ids.split(",");
        List<String> listId = Stream.of(strIdsArr).collect(Collectors.toList());
//        List<Object> listId = Stream.of(strIdsArr).collect(Collectors.toList());
//        Example example = new Example(Product.class);
//        example.createCriteria().andIn("id", listId);
//        //批量选择性非空属性修改
//        productDao.updateByExampleSelective(product, example);
        productDao.updateIsShowOrHide(product, listId);

        //将上架的商品查出来存到solr服务器中中和生成单个商品详情页面，将商品ids发到mq中
        if (product.getIsShow() == 1 && product.getIsShow() != null) {
            //监听目标名称
            jmsTemplate.send("productIds", new MessageCreator() {

                @Override
                public Message createMessage(Session session) throws JMSException {
                    //使用session创建文本信息
                    return session.createTextMessage(ids);
                }

            });
        }


//        //如果是商品上架，将商品信息添加到solr服务器中
//        //需要保存的信息有：商品id，商品名称，图片地址，售价，品牌id，上架时间
//        if(product.getIsShow() == 1){
//            //查询ids中的所有商品信息
//            List<Product> products = productDao.selectByExample(example);
//            try {
//                for (Product product2 : products) {
//                    //将商品的信息，添加到sorl文档对象
//                    SolrInputDocument document = new SolrInputDocument();
//                    document.addField("id", product2.getId());
//                    document.addField("name_ik", product2.getName());
//                    document.addField("url", product2.getImgUrl().split(",")[0]);
//                    document.addField("brandId", product2.getBrandId());
//                    document.addField("createTime", product2.getCreateTime());
//                    //SELECT * FROM `bbs_sku` WHERE bbs_sku.product_id=449 ORDER BY price ASC LIMIT 1,1
//                    //查出某商品库存中的最低价格
//                    Example example2 = new Example(Sku.class);
//                    example2.createCriteria().andEqualTo("productId", product2.getId());
//                    example2.setOrderByClause("price asc");
//                    //开始分页limit
//                    PageHelper.startPage(1, 1);
//                    //查询出某商品的库存
//                    List<Sku> skus = skuDao.selectByExample(example2);
//                    //结束分页
//                    PageHelper.endPage();
//                    //取出对应商品库存第一条数据的价格
//                    document.addField("price", skus.get(0).getPrice());
//                    
//                    //将文档对象存到solr库中
//                    solrServer.add(document);
//                    
//                    //提交
//                    solrServer.commit();
//                    
////                    <delete><query>*:*</query></delete>
////                    <commit/>
//                }
//            } catch (SolrServerException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }

    /**
     * 根据商品id查询出单个商品详情信息（商品编号，商品名称，商品颜色，商品尺寸，商品价格）
     *
     * @param productId
     * @return
     */
    public SuperPojo findSingleProductById(Long productId) {
        //查询出商品信息
//        Product product = productDao.selectByPrimaryKey(productId);
        Product product = productDao.findProductInfoById(productId);
        //查询出库存信息的价格及库存关联的颜色名称
        List<SuperPojo> skus = skuDao.findSkuAndColorByProductId(productId);

        //将商品和库存信息封装到实体类中回显到单个商品详情页面
        SuperPojo singleProductDetail = new SuperPojo();
        singleProductDetail.setProperty("product", product);
        singleProductDetail.setProperty("skus", skus);

        return singleProductDetail;

    }


}
