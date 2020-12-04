package com.gtmc.carapp.service.workorder.service;

import com.gtmc.carapp.service.workorder.entity.EsignConOrderVo;
import com.gtmc.carapp.service.workorder.entity.HistoryConOrderVo;
import com.gtmc.carapp.service.workorder.entity.TtEsignConstructionOrder;
import com.gtmc.carapp.service.workorder.entity.TtEsignConstructionOrderDetail;
import com.gtmc.carapp.service.workorder.mapper.TtEsignConstructionOrderDetailMapper;
import com.gtmc.carapp.service.workorder.mapper.TtEsignConstructionOrderMapper;
import com.yonyou.cloud.common.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TtEsignConstructionOrderService extends BaseService<TtEsignConstructionOrderMapper, TtEsignConstructionOrder> {

    private Logger logger = Logger.getLogger(this.getClass());

    @Resource
    private TtEsignConstructionOrderDetailMapper ttEsignConstructionOrderDetailMapper;
    @Autowired
    private Environment env;

    SimpleDateFormat formatter  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");

    /**
     * 查询施工单详情/历史
     * @param orderNumber 工单号
     * @return
     */
    public List<HistoryConOrderVo> selectConstructionOrderInfo(String orderNumber){
        logger.info("查询施工单详情/历史 Service Start");
        logger.info("查询施工单详情/历史 Service param orderNumber = "+orderNumber);

        List<HistoryConOrderVo> historyConOrderVoArrayList = new ArrayList<HistoryConOrderVo>();
        HistoryConOrderVo historyConOrderVo ;

        try {
            /**
             * 查询当前已签字施工单历史
             * 施工单工单号等于入参工单号 && 删除状态为无效 && 工单状态为已签字
             */
            List<TtEsignConstructionOrder> ttEsignConstructionOrders =  mapper.selectSigned(orderNumber);
            logger.info("查询当前已签字施工单历史 size ="+ttEsignConstructionOrders.size());
            if (ttEsignConstructionOrders.size()>0) {
                for (TtEsignConstructionOrder tempOrder : ttEsignConstructionOrders) {
                    historyConOrderVo = new HistoryConOrderVo();
                    historyConOrderVo.setHtoInfoPdfUrl(tempOrder.getUnsignFileUrl());
                    if (null != tempOrder.getCreateDate())
                        historyConOrderVo.setCreateDate(formatter.format(tempOrder.getCreateDate()));
                    if (null != tempOrder.getSignDate())
                        historyConOrderVo.setAutographDate(formatter.format(tempOrder.getSignDate()));
                    historyConOrderVoArrayList.add(historyConOrderVo);
                }
            }

        } catch (Exception e) {
            logger.info("查询施工单详情/历史 error："+e.getMessage());
            logger.info("查询施工单详情/历史 error："+e);
            e.printStackTrace();
        }

        logger.info("查询施工单详情/历史 Service End");
        return historyConOrderVoArrayList;
    }

    /**
     * 获取施工单状态
     * @param orderNumer 工单号
     * @return
     */
    public Integer getOrderStatus(String orderNumer){
        logger.info("查询施工单详情/历史 Service Start");
        logger.info("查询施工单详情/历史 Service param orderNumer = "+orderNumer);
        try {
            TtEsignConstructionOrder ttEsignConstructionOrder = new TtEsignConstructionOrder();
            ttEsignConstructionOrder.setSrvOrderNo(orderNumer);
            ttEsignConstructionOrder.setDeleteFlag(0);
            List<TtEsignConstructionOrder> ttEsignConstructionOrderList = mapper.select(ttEsignConstructionOrder);
            if (ttEsignConstructionOrderList.size() > 0)
                return ttEsignConstructionOrderList.get(0).getStatus();
        } catch (Exception e) {
            logger.info("查询施工单详情/历史 error："+e.getMessage());
            logger.info("查询施工单详情/历史 error："+e);
            e.printStackTrace();
        }

        logger.info("查询施工单详情/历史 Service End");
        return -1;
    }

    /**
     * H5获取施工单模板数据
     * @param orderNumer 工单号
     * @return
     */
    public TtEsignConstructionOrderDetail getConOrderInfo(String orderNumer){
        logger.info("H5获取施工单模板数据 Service Start ");
        logger.info("H5获取施工单模板数据 Service param orderNumer = "+orderNumer);
        TtEsignConstructionOrderDetail ttEsignConstructionOrderDetail = null;
        try {
            TtEsignConstructionOrder ttEsignConstructionOrder = new TtEsignConstructionOrder();
            ttEsignConstructionOrder.setSrvOrderNo(orderNumer);
            ttEsignConstructionOrder.setDeleteFlag(0);
            ttEsignConstructionOrder.setStatus(1);

            List<TtEsignConstructionOrder> ttEsignConstructionOrders =  mapper.select(ttEsignConstructionOrder);
            logger.info("H5获取施工单模板数据 size ="+ttEsignConstructionOrders.size());
            if (ttEsignConstructionOrders.size()>0) {
                Long conOrderNubmer = ttEsignConstructionOrders.get(0).getId();
                ttEsignConstructionOrderDetail = new TtEsignConstructionOrderDetail();
                ttEsignConstructionOrderDetail.setConstructionOrderId(conOrderNubmer);
                List<TtEsignConstructionOrderDetail> ttEsignConstructionOrderDetails = ttEsignConstructionOrderDetailMapper.select(ttEsignConstructionOrderDetail);
                if (ttEsignConstructionOrderDetails.size()>0)
                    return ttEsignConstructionOrderDetails.get(0);
            }
        } catch (Exception e) {
            logger.info("查询施工单详情/历史 error："+e.getMessage());
            logger.info("查询施工单详情/历史 error："+e);
            e.printStackTrace();
        }

        logger.info("查询施工单详情/历史 Service End");
        return ttEsignConstructionOrderDetail;
    }

    /**
     * 获取施工单详情
     * @param orderNumber 工单号
     * @return
     */
    public EsignConOrderVo selectConOrderInfo(String orderNumber){
        logger.info("获取施工单详情 Service Start");
        logger.info("获取施工单详情 Service param orderNumer = "+orderNumber);
        TtEsignConstructionOrder ttEsignConstructionOrder = new TtEsignConstructionOrder();
        EsignConOrderVo esignConOrderVo = new EsignConOrderVo();
        try {
            ttEsignConstructionOrder.setSrvOrderNo(orderNumber);
            ttEsignConstructionOrder.setDeleteFlag(0);

            List<TtEsignConstructionOrder> ttEsignConstructionOrders =  mapper.select(ttEsignConstructionOrder);
            logger.info("获取施工单详情 size ="+ttEsignConstructionOrders.size());
            if (ttEsignConstructionOrders.size()>0) {
                ttEsignConstructionOrder = ttEsignConstructionOrders.get(0);
                logger.info("获取施工单详情 status ="+ttEsignConstructionOrder.getStatus());
                //待签字
                if (ttEsignConstructionOrder.getStatus() == 1){
                    ttEsignConstructionOrder.setSignedFileUrl(env.getProperty("url.getHtml.path"));
                }
                logger.info("获取施工单详情 Vo转换");
                esignConOrderVo.setConOrderNumber(ttEsignConstructionOrder.getId());
                esignConOrderVo.setDeleteFlag(ttEsignConstructionOrder.getDeleteFlag());
                esignConOrderVo.setSignedFileUrl(ttEsignConstructionOrder.getSignedFileUrl());
                esignConOrderVo.setUnsignFileUrl(ttEsignConstructionOrder.getUnsignFileUrl());
                esignConOrderVo.setStatus(ttEsignConstructionOrder.getStatus());
                if (null != ttEsignConstructionOrder.getSignDate())
                    esignConOrderVo.setSignDate(formatter.format(ttEsignConstructionOrder.getSignDate()));
                if (null != ttEsignConstructionOrder.getCreateDate())
                    esignConOrderVo.setCreateDate(formatter.format(ttEsignConstructionOrder.getCreateDate()));
                if (null != ttEsignConstructionOrder.getUpdateDate())
                    esignConOrderVo.setUpdateDate(formatter.format(ttEsignConstructionOrder.getUpdateDate()));

                logger.info("获取施工单详情 获取是否有历史已签署施工单");
                esignConOrderVo.setIsHistoryFile((mapper.selectSigned(orderNumber).size() > 0)?1:0);

            }
        } catch (Exception e) {
            logger.info("查询施工单详情/历史 error："+e.getMessage());
            logger.info("查询施工单详情/历史 error："+e);
            e.printStackTrace();
        }

        logger.info("查询施工单详情/历史 Service End");
        return esignConOrderVo;
    }
    /**
     * 保存电子签名协议
     * @param phone
     * @param userId
     */
    public void saveAuthorizedServices(String phone,int userId){
    	//查询是否存在协议
    	List<Map<String,Object>> list=mapper.queryAuthorizedServices(phone, userId);
    	//存在则修改  不存在新增
    	if(list.size()>0){
    		mapper.updateAuthorizedServices(phone, userId);
    	}else{
    		mapper.saveAuthorizedServices(phone, userId);
    	}
	}
    /**
     * 保存意愿签署
     * @param id
     * @param userId
     */
    public void saveWillingnessToSign(Long id,int userId){
    	mapper.saveWillingnessToSign(id,userId);
    }
//    /**
//	 * html转pdf文件解决中文无法显示问题
//	 * @return
//	 */
//	public static final class ChinaFontProvide implements FontProvider {
//
//		@Override
//		public Font getFont(String arg0, String arg1, boolean arg2, float arg3, int arg4, BaseColor arg5) {
//			BaseFont bfChinese = null;
//			try {
//				bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//				//也可以使用Windows系统字体(TrueType)
//		        //bfChinese = BaseFont.createFont("C:/WINDOWS/Fonts/SIMYOU.TTF", BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			Font FontChinese = new Font(bfChinese, 20, Font.NORMAL);
//			return FontChinese;
//		}
//
//		@Override
//		public boolean isRegistered(String arg0) {
//			return false;
//		}
//	}
//	/**
//	 * html转pdf文件
//	 * @param htmlPath h5链接
//	 * @return
//	 */
//	public String htmlToPdf(String htmlPath){
//			try {
//				//第一步，创建一个 iTextSharp.text.Document对象的实例：
//		        Document document = new Document();
//		        //第二步，为该Document创建一个Writer实例：
//		        PdfWriter writer;
//				writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\test.pdf"));
//				//第三步，打开当前Document
//		        document.open();
//		        //解析 html文件 去除script标签
//		        BufferedReader bis = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\new_file.html")));
//		        StringBuilder szContent = new StringBuilder();
//		        String szTemp;
//		        while ((szTemp = bis.readLine()) != null) {
//		            szContent.append(szTemp);
//		        }
//		        String htmlStr=szContent.toString();
//		        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
//		        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
//		        Matcher m_script=p_script.matcher(htmlStr);
//		        htmlStr=m_script.replaceAll(""); //过滤script标签
//		        //字符串转成html文件
//
//
//		        //第四步，为当前Document添加内容：
//		        //document.add(new Paragraph("Hello World"));
//		        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(htmlPath),null, new ChinaFontProvide());
//		        //第五步，关闭Document
//		        document.close();
//		        System.out.println( "OK!" );
//			} catch (FileNotFoundException | DocumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return htmlPath;
//		}
}
