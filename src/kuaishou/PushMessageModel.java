package kuaishou;

import java.util.Map;

public class PushMessageModel {
    /**
     * push business type 默认为快手主app，目前还支持GAME
     */
//    private InfraPushCommon.BusinessType businessType = KUAISHOU;

    /**
     * push app 类型
     */
//    private RecoPushBase.PushAppType pushAppType = KUAISHOU_MAIN;

    /**
     * push业务名
     */
    private String promotionPushBiz;

    /**
     * 发送消息的类型，目前有push和短信两种类型，默认为push
     */
//    private MessageType messageType;
    /**
     * 若为发送短信类型，需要设置短信消息模型
     */
//    private SmsModel smsModel;
    /**
     * push类型
     */
//    private PushType pushTypeEnum;

    /**
     * 接收方userId
     */
    private long userId;
    /**
     * 接收方deviceId
     */
    private String deviceId;
    /**
     * 若push的是视频，这里为视频id，若push的是直播，这里是直播id
     */
    private long photoId;
    /**
     * 发送者Id，可以为0
     */
    private long senderId;
    /**
     * 消息标题
     */
    private String title;
    /**
     * 发送push时的消息摘要
     */
    private String body;
    /**
     * 如果messageId与keywork非空，messageI必须包含keywork
     */
    private String messageId;
    /**
     * 消息id关键字
     */
//    private PushMessageIdKeyworkEnum keywork;
    /**
     * push落地的链接
     */
    private String rawUrl;
    /**
     * push消息小图
     */
    private String smallPicture;

    /**
     * push大图
     */
    private String largePicture;
    /**
     * 直播push相关信息，如果push消息为直播类型，需要设置该字段
     */
//    private LiveStreamModel liveStreamModel;
    /**
     * 是否带频控的push,默认为false,即有频控
     */
    private boolean noQuotaControlFlag;

    /**
     * push截止时间，即在这个时间之后，保证push不再发送，主要用来设置有效期，用截止时间减去发送push时的当前时间即为该条push的有效期
     */
    private long pushEndTime;

    /**
     * push开始发送时间
     */
    private long pushStartTime;

    /**
     * 用于实时监控的tag
     */
    private String tag;

    /**
     * 测试环境topic数据是否可以外发，默认不允许
     */
    private boolean testEnvSend = false;

    /**
     * 是否是发给新用户，默认false
     */
    private boolean newUserPush = false;

    /**
     * 是否是发给新设备，默认false
     */
    private boolean newDevicePush = false;

    /**
     * 发送比例，默认100
     */
    private int pushPercent = 100;

    /**
     * 是否使用hive
     */
    private boolean useHive = false;

    /**
     * 扩展字段，A站的target_type就可以放到这里面
     */
//    private Map<String, String> extraInfo = Maps.newHashMap();

    /**
     * 机型发送类型
     */
    private String mobileType;

}
