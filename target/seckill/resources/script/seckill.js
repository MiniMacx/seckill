//存放主要交互逻辑js代码
//javascript模块化
//seckill.detail.init(params);
var seckill= {
    //封装秒杀相关ajax的url
    URL: {
        now:function () {
            return '/seckill/time/now';
        },
        exposer:function (seckillId) {
            return '/seckill/'+seckillId+'/exposer';
        },
        execution:function (seckillId,md5) {
            return '/seckill/'+seckillId+'/'+md5+'/execution';
        }
    },
    handleSeckillkill:function (seckillId,node) {
        //获取秒杀地址，进行秒杀
        node.hide()
            .html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
        $.get(seckill.URL.exposer(seckillId),{},function (result) {
            //在回调函数中执行交互流程
            if(result&&result['success']){
                var exposer=result['data'];
                if(exposer['exposed']){
                    //开启秒杀
                    //获取秒杀地址,
                    var md5=exposer['md5'];

                    var killUrl=seckill.URL.execution(seckillId,md5);
                    console.log("killUrl"+killUrl);
                    //绑定一次点击事件
                    $('#killBtn').one('click',function () {
                        //执行秒杀
                        //1.先禁用按钮

                        $(this).addClass('disabled');
                        //2.发送秒杀请求执行秒杀
                        $.post(killUrl,{},function (result) {
                           if(result &&result['success']){
                               var killResult=result['data'];
                               var state =killResult['state'];
                               var stateInfo=killResult['stateInfo'];
                               //3.显示秒杀结果

                               node.html('<span class="label label-success">'+stateInfo+'</span>');
                           }else if(!result['success']){
                               alert("您已秒杀此产品，请勿重复秒杀！");
                           }
                        });

                    });
                    node.show();
                }else{
                    //未开启秒杀
                    var now=exposer['now'];
                    var start=exposer['start'];
                    var end=exposer['end'];
                    seckill.countdown(seckillId,now,start,end);
                }
            }else {
                console.log('result'+result);
            }

        });
    },

    //验证手机号
    validatePhone: function (phone) {
        if (phone && phone.length == 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },

    countdown:function (seckillId,nowTime,startTime,endTime) {
        //时间判断
        var seckillBox=$('#seckill-box');

        if(nowTime>endTime){
            //秒杀结束
           seckillBox.html('秒杀结束！');
        }else if(nowTime<startTime){

            //秒杀未开始，计时,利用jquery插件
            var killTime=new Date(startTime+1000);
            seckillBox.countdown(killTime,function (event) {
                var format=event.strftime('秒杀计时:%D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish countdown',function () {
                seckill.handleSeckillkill(seckillId,seckillBox);
            });
        }else{
            //秒杀开始
            seckill.handleSeckillkill(seckillId,seckillBox);
        }
    },

    //详情页秒杀逻辑
    detail: {
        //详情页初始化
        init: function (params) {
            //手机验证和登录，计时交互
            //规划交互流程
            var killPhone = $.cookie('killPhone');
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];

            if (!seckill.validatePhone(killPhone)) {
                //绑定手机号
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show: true,
                    backdrop: 'static',
                    keyboard: false
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    console.log('inputPhone='+inputPhone);//TODO
                    if (seckill.validatePhone(inputPhone)) {
                        //电话写入cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //刷新页面
                        window.location.reload();
                    } else {
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误！</label>').show(300);
                    }
                });
            }
            //已经登录
            //计时交互

            $.get(seckill.URL.now(),{},function (result) {

                if(result && result['success']){
                    var nowTime=result['data'];
                    //时间判断，根据目前系统时间做出流程控制
                    //计时交互

                    seckill.countdown(seckillId,nowTime,startTime,endTime);
                }
                else {
                    console.log('result'+result);
                }
            });

        }
    }
}