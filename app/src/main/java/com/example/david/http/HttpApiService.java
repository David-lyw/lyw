package com.example.david.http;

import com.example.david.Dto.ActivityImageDto;
import com.example.david.Dto.CollectionDto;
import com.example.david.Dto.FAQDto;
import com.example.david.Dto.FeedBackDto;
import com.example.david.Dto.GetCashDto;
import com.example.david.Dto.ListDto;
import com.example.david.Dto.ReceiverDto;
import com.example.david.Dto.RecordDto;
import com.example.david.Dto.WxLoginBean;
import com.example.david.Dto.WxUserIdLoginBean;
import com.example.david.bean.AddressBean;
import com.example.david.bean.CategoryBean;
import com.example.david.bean.ChangeUserProfileBean;
import com.example.david.bean.JoinBean;
import com.example.david.bean.MessageBean;
import com.example.david.bean.NewsBean;
import com.example.david.bean.RankingDataBean;
import com.example.david.bean.SoonBean;
import com.example.david.bean.TopShowBean;
import com.example.david.bean.UpgradeReceiveBean;
import com.example.david.bean.UpgradeSendBean;
import com.example.david.bean.WinningBean;
import com.example.david.bean.rankingBean;
import com.example.david.bean.user.UserBean;
import com.lyw.http.HttpResult;



import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by json on 2016/09/29.
 */

public interface HttpApiService {


    /**
     * 2.4 用户退出登录
     * http://emall.eteclab.com/api/v1/user/logout
     * 退出登录
     */
    @GET("user/logout")
    Observable<HttpResult> userLoginOut(@Query("token") String token);


    /**
     * 2.8 获取用户资料
     * 2.9修改用户资料
     * http://emall.eteclab.com/api/v1/user/profile
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("user/profile")
    Observable<HttpResult> userProfile(@Body ChangeUserProfileBean object
    );

    /**
     * 2.8:
     * http://emall.eteclab.com/api/v1/user/profile
     *
     * @param userId 项目用户Idss
     * @return
     */
    @GET("user/profile")
    Observable<HttpResult<UserBean>> getUserProfile(@Query("userId") String userId);


    /**
     * 2.10 修改用户头像
     * 修改用户头像
     * http://emall.eteclab.com/api/v1/user/portrait
     */
    @Multipart
    @POST("user/uploadPortrait/{userId}")
    Observable<HttpResult> userProtrait(@Path("userId") String userId,
                                        @Part("file\"; filename=\"avatar.png") RequestBody file
    );


    /**
     * 2.13 获取用户收货地址
     * 获取用户地址列表
     * http://emall.eteclab.com/api/v1/user/address
     */
    @GET("user/address")
    Observable<HttpResult<List<AddressBean>>> userAddress(@Query("userId") String userId,
                                                          @Query("page") String page,
                                                          @Query("size") String size
    );

    /**
     * 2.14添加/修改收货地址
     * 地址：http://emall.eteclab.com/api/v1/user/address
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("user/address")
    Observable<HttpResult> userSetAddress(@Body AddressBean bean
    );

    /**
     * 2.15删除收货地址
     * 2.15.1接口说明
     * 地址：http://emall.eteclab.com/api/v1/user/address/delete
     */
    @GET("user/address/delete")
    Observable<HttpResult> userDeleteAddress(@Query("userId") String userId,
                                             @Query("id") String id
    );

    /**
     * 2.16设置默认收货地址
     * 2.16.1接口说明
     * 地址：http://emall.eteclab.com/api/v1/user/address/default
     */
    @GET("user/address/default")
    Observable<HttpResult> userDefaultAddress(@Query("userId") String userId,
                                              @Query("id") String id
    );

    /**
     * 2.17获取用户参与夺宝活动记录
     * 2.17.1接口说明
     * 地址：http://emall.eteclab.com/api/v1/user/activity
     * status:1 待揭晓  2 待领取  3待晒单  4未中奖  5 已中奖
     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("user/activity")
    Observable<HttpResult<List<WinningBean>>> userGetWinning(@Query("userId") String userId,
                                                             @Query("status") String status,
                                                             @Query("page") String page,
                                                             @Query("size") String size
    );

    /**
     * 2.23 发布晒单
     * http://emall.eteclab.com/api/v1/userNews
     */
    @Multipart
    @POST("userNews")
    Observable<HttpResult> showBill(
            @Part("userId") RequestBody faultId,
            @Part("activityId") RequestBody projectCode,//必填
            @Part("content") RequestBody groupsCode,//必填
            @Part List<MultipartBody.Part> list //
    );


    /**
     * 2.24消息列表
     * 2.24.1接口说明
     * 地址：http://emall.eteclab.com/api/v1/user/notice
     */
    @GET("user/notice")
    Observable<HttpResult<List<MessageBean>>> userNotice(@Query("userId") String userId,
                                                         @Query("type") String type,
                                                         @Query("page") String page,
                                                         @Query("size") String size
    );

    /**
     * 2.25用户参加活动
     * http://emall.eteclab.com/api/v1/user/notice/{noticeId}
     * 请求类型：HTTP	POST方式
     */
    //@Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("user/notice/{noticeId}")
    Observable<HttpResult<MessageBean>> NoticeDetail(@Path("noticeId") String noticeId);

    /**
     * 2.26用户参加活动
     * 2.25.1接口说明
     * 地址：http://emall.eteclab.com/api/v1/user/ join/ {userId}
     * 请求类型：HTTP	POST方式
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("user/join/{userId}")
    Observable<HttpResult> userJoin(
            @Path("userId") String userId,
            @Body List<JoinBean> list
    );

    /**
     * 2.27用户领取奖品
     * 2.26.1接口说明
     * 地址：http://emall.eteclab.com/api/v1/user/receiveAward
     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @GET("user/receiveAwardOne")
    Observable<HttpResult> userReceiveAward(
            @Query("userId") String userId,
            @Query("activityId") String activityId,
            @Query("addressId") String addressId
    );

    /**
     * 2.27用户领取奖品(后台直接验证了用户信息是否完善。)
     * 2.26.1接口说明
     * http://emall.eteclab.com/api/v1/user/checkUser
     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @GET("user/checkUser")
//    Observable<HttpResult> userCheckUser(
//            @Query("userId") String userId
//    );

    /**
     * 2.28:用户微信登录
     * http://emall.eteclab.com/api/v1/user/weixin/login
     */
    //@Multipart
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("user/weixin/login")
    Observable<WxUserIdLoginBean> weixinlogin(
//            @Part("openId") RequestBody openId,
//            @Part("unionId") RequestBody unionId,//必填
//            @Part("nickName") RequestBody nickName,//必填
//            @Part("sex") RequestBody sex, //
//            @Part("portrait") RequestBody portrait
            @Body WxLoginBean bean
    );


    /**
     * 2.30:验证用户信息完整
     */
    @GET("user/checkUser")
    Observable<HttpResult> userCheckUser(
            @Query("userId") String userId
    );

    /**
     * 3.1 获取软件最新版本信息
     * http://emall.eteclab.com/api/v1/app/upgrade
     * http://emall.eteclab.com/api/v1/app/upgrade
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("app/upgrade")
    Observable<HttpResult<UpgradeReceiveBean>> upgrade(@Body UpgradeSendBean bean);


    /**
     * 3.8 获取常见问题
     * http://emall.eteclab.com/api/v1/app/question
     */
    @GET("app/question")
    Observable<HttpResult<ArrayList<FAQDto>>> getUsualQuestion(@Query("appkey") String appkey);

    /**
     * 3.9 提交意见反馈信息
     * http://emall.eteclab.com/api/v1/app/feedback
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("app/feedback")
    Observable<HttpResult> feedBackInfo(@Body FeedBackDto dto);

    /**
     * 4.1 首页轮播图
     * //    http://emall.eteclab.com/api/v1/topshow
     *
     * @param page
     * @param size
     * @return
     */
    @GET("topshow")
    Observable<HttpResult<List<TopShowBean>>> topshow(@Query("page") String page,
                                                      @Query("size") String size);


    /**
     * 4.4 获取首页排行
     * //    http://emall.eteclab.com/api/v1/ranking
     *
     * @param page
     * @param size
     * @return
     */
    @GET("ranking")
    Observable<HttpResult<List<rankingBean>>> ranking(@Query("page") String page,
                                                      @Query("size") String size);

    /**
     * 4.5 获取排行数据
     * //   http://emall.eteclab.com/api/v1/rankingList?rankingId=1&page=1&size=20
     *
     * @param page
     * @param size
     * @return
     * @Query("userId") String userId, :不是必须参数
     */
    @GET("rankingList")
    Observable<HttpResult<List<RankingDataBean>>> rankingList(@Query("rankingId") String rankingId,
                                                              @Query("userId") String userId,
                                                              @Query("page") String page,
                                                              @Query("size") String size);

    /**
     * 4.2 分类
     * <p>
     * 无参
     *
     * @return
     */
    @GET("category")
    Observable<HttpResult<List<CategoryBean>>> category();

    /**
     * 4.2 分类
     *
     * @return
     */
    @GET("category")
    Observable<HttpResult<List<CategoryBean>>> category(@Query("categoryId") String id);

    /**
     * 4.6 获取分类下活动.----搜索
     * http://emall.eteclab.com/api/v1/activity?categoryId=1
     */
    @GET("activity")
    Observable<HttpResult<List<RankingDataBean>>> getActivity(@Query("categoryId") String categoryId,
                                                              @Query("keyword") String keyword,
                                                              @Query("page") String page,
                                                              @Query("size") String size);

    /**
     * 4.6 获取分类下活动.----4.2分类。
     * http://emall.eteclab.com/api/v1/activity?categoryId=1
     */
    @GET("activity")
    Observable<HttpResult<List<RankingDataBean>>> getActivity1(@Query("categoryId") String categoryId,
                                                               @Query("userId") String keyword,
                                                               @Query("page") String page,
                                                               @Query("size") String size);


    /**
     * 4.7 活动详情
     * http://emall.eteclab.com/api/v1/activity/4
     */

    @GET("activity/{id}")
    Observable<HttpResult<RankingDataBean>> getActivityDetail(@Path("id") String id,
                                                              @Query("userId") String userId);

    /**
     * 4.7 .1活动详情
     * http://emall.eteclab.com/api/v1/activity/4
     */

    @GET("activity/{id}")
    Observable<HttpResult<WinningBean>> getOrderDetail(@Path("id") String id,
                                                       @Query("userId") String userId);

    /**
     * 4.8 本期夺宝活动参与记录
     * http://emall.eteclab.com/api/v1/activity/user?activityId=1
     */
    @GET("activity/user")
    Observable<HttpResult<ArrayList<RecordDto>>> getRecord(@Query("activityId") String activityId,
                                                           @Query("page") String page,
                                                           @Query("size") String size);

    /**
     * 4.10 活动产品图片列表
     * http://emall.eteclab.com/api/v1/product/images?productId=4
     * HttpResult<ArrayList<ActivityImageDto>>
     */
    @GET("product/images")
    Observable<HttpResult<ArrayList<ActivityImageDto>>> getImageList(@Query("productId") String productId);

    /**
     * 4.11 最新揭晓
     * http://emall.eteclab.com/api/v1/activity/unveiled
     */
    @GET("activity/unveiled")
    Observable<HttpResult<List<SoonBean>>> getUnveiled(@Query("userId") String userId,
                                                       @Query("page") String page,
                                                       @Query("size") String size);

    /**
     * 4.12 获取清单列表
     * http://emall.eteclab.com/api/v1/user/shoppingcart?appkey=3z9jimbc8irarq6t6ty2wrwyc564mgt5&userId=203&page=1&size=20
     */
    @GET("user/shoppingcart")
    Observable<HttpResult<ArrayList<ListDto>>> getList(@Query("appkey") String appkey,
                                                       @Query("userId") String userId,
                                                       @Query("page") String page,
                                                       @Query("size") String size);

    /**
     * 4.15 新闻列表
     * http://emall.eteclab.com/api/v1/news
     */
    @GET("news")
    Observable<HttpResult<ArrayList<NewsBean>>> getNews(@Query("type") String type,
                                                        @Query("page") String page,
                                                        @Query("size") String size);

    /**
     * 4.16 新闻详情
     * http://emall.eteclab.com/api/v1/news/{1}
     */
    @GET("news/{id}")
    Observable<HttpResult<NewsBean>> getNewsDetail(@Path("id") String id);


    /**
     * 5.4 提现申请
     * http://emall.eteclab.com/api/v1/userMoney/txMoney
     */
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("userMoney/txMoney")
    Observable<HttpResult> txMoney(@Body GetCashDto dto);

    /**
     * 5.5 账单列表
     * http://emall.eteclab.com/api/v1/userMoney
     */
    @GET("userMoney")
    Observable<HttpResult<List<ReceiverDto>>> getUserMoney(@Query("userId") String userId,
                                                           @Query("page") String page,
                                                           @Query("size") String size
    );


    /**
     * #后加的接口
     * #1:前往新一期
     * http://emall.eteclab.com/api/v1/activity/next?userId=203&activityId=1018
     */
    @GET("activity/next")
    Observable<HttpResult<RankingDataBean>> getNextActivityDetail(@Query("userId") String userId,
                                                                  @Query("activityId") String id);


    /**
     * 2.18 获取用户收藏记录(关注 模块)
     * http://emall.eteclab.com/api/v1/user/collectionList
     */
    @GET("user/collectionList")
    Observable<HttpResult<List<CollectionDto>>> getCollectionList(@Query("userId") String userId,
                                                                  @Query("page") String page,
                                                                  @Query("size") String size);

}
