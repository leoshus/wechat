var _readyMessageIframe, _sendMessageQueue = [],
    _receiveMessageQueue = [],
    _callback_count = 1000,
    _callback_map = {},
    _event_hook_map = {},
    _session_data = {},
    _MESSAGE_SEPERATOR = '__wxmsg_sep__',
    _CUSTOM_PROTOCOL_SCHEME = 'weixin',
    _MESSAGE_TYPE = '__msg_type',
    _CALLBACK_ID = '__callback_id',
    _EVENT_ID = '__event_id',
    _QUEUE_HAS_MESSAGE = 'dispatch_message/',
    _setResultIframe; // 用于替代addJavascriptInterface的替代方案，返回值通过修改该iframe src实现
//创建ifram的准备队列
 
function _createQueueReadyIframe(doc) {
    // _setResultIframe 的初始化
    _setResultIframe = doc.createElement('iframe');
    _setResultIframe.id = '__WeixinJSBridgeIframe_SetResult';
    _setResultIframe.style.display = 'none';
    doc.documentElement.appendChild(_setResultIframe);
 
    _readyMessageIframe = doc.createElement('iframe');
    _readyMessageIframe.id = '__WeixinJSBridgeIframe';
    _readyMessageIframe.style.display = 'none';
    doc.documentElement.appendChild(_readyMessageIframe);
    return _readyMessageIframe;
}
//将消息添加到发送队列，iframe的准备队列为weixin://dispatch_message/
 
function _sendMessage(message) {
    _sendMessageQueue.push(message);
    _readyMessageIframe.src = _CUSTOM_PROTOCOL_SCHEME + '://' + _QUEUE_HAS_MESSAGE;
 
    // var ifm = _WXJS('iframe#__WeixinJSBridgeIframe')[0];
    // if (!ifm) {
    //   ifm = _createQueueReadyIframe(document);
    // }
    // ifm.src = _CUSTOM_PROTOCOL_SCHEME + '://' + _QUEUE_HAS_MESSAGE;
};
//取出队列中的消息，并清空接收队列
 
function _fetchQueue() {
    var messageQueueString = JSON.stringify(_sendMessageQueue);
    _sendMessageQueue = [];
    //window.JsApi && JsApi.keep_setReturnValue && window.JsApi.keep_setReturnValue('SCENE_FETCHQUEUE', messageQueueString);
    _setResultValue('SCENE_FETCHQUEUE', messageQueueString);
    return messageQueueString;
};
 
function _handleMessageFromWeixin(message) {
    var msgWrap = message;
 
    switch (msgWrap[_MESSAGE_TYPE]) {
    case 'callback':
        {
            if (typeof msgWrap[_CALLBACK_ID] === 'string' && typeof _callback_map[msgWrap[_CALLBACK_ID]] === 'function') {
                var ret = _callback_map[msgWrap[_CALLBACK_ID]](msgWrap['__params']);
                delete _callback_map[msgWrap[_CALLBACK_ID]]; // can only call once
                //window.JsApi && JsApi.keep_setReturnValue && window.JsApi.keep_setReturnValue('SCENE_HANDLEMSGFROMWX', JSON.stringify(ret));
                _setResultValue('SCENE_HANDLEMSGFROMWX', JSON.stringify(ret));
                return JSON.stringify(ret);
            }
            //window.JsApi && JsApi.keep_setReturnValue && window.JsApi.keep_setReturnValue('SCENE_HANDLEMSGFROMWX', JSON.stringify({'__err_code':'cb404'}));
            _setResultValue('SCENE_HANDLEMSGFROMWX', JSON.stringify({
                '__err_code': 'cb404'
            }));
            return JSON.stringify({
                '__err_code': 'cb404'
            });
        }
        break;
    case 'event':
        {
            if (typeof msgWrap[_EVENT_ID] === 'string' && typeof _event_hook_map[msgWrap[_EVENT_ID]] === 'function') {
                var ret = _event_hook_map[msgWrap[_EVENT_ID]](msgWrap['__params']);
                //window.JsApi && JsApi.keep_setReturnValue && window.JsApi.keep_setReturnValue('SCENE_HANDLEMSGFROMWX', JSON.stringify(ret));
                _setResultValue('SCENE_HANDLEMSGFROMWX', JSON.stringify(ret));
                return JSON.stringify(ret);
            }
            //window.JsApi && JsApi.keep_setReturnValue && window.JsApi.keep_setReturnValue('SCENE_HANDLEMSGFROMWX', JSON.stringify({'__err_code':'ev404'}));
            _setResultValue('SCENE_HANDLEMSGFROMWX', JSON.stringify({
                '__err_code': 'ev404'
            }));
            return JSON.stringify({
                '__err_code': 'ev404'
            });
        }
        break;
    }
};
 
function _setResultValue(scene, result) {
    if (result === undefined) {
        result = 'dummy';
    }
    _setResultIframe.src = 'weixin://private/setresult/' + scene + '&' + base64encode(UTF8.encode(result));
    //_setResultIframe.src = 'weixin://private/setresult/' + scene + '&' + window.btoa(result);
}
 
function _env(key) {
    return _session_data[key];
}
 
function _log(fmt) {
    var argv = [];
    for (var i = 0; i < arguments.length; i++) {
        argv.push(arguments[i]);
    };
    var fm = argv.shift();
    var msg;
    try {
        msg = vsprintf(fm, argv);
    } catch (e) {
        msg = fmt;
    }
    _call('log', {
        'msg': msg
    });
}
 
function _call(func, params, callback) {
    if (!func || typeof func !== 'string') {
        return;
    };
    if (typeof params !== 'object') {
        params = {};
    };
 
    var callbackID = (_callback_count++).toString();
 
    if (typeof callback === 'function') {
        _callback_map[callbackID] = callback;
    };
 
    var msgObj = {
        'func': func,
        'params': params
    };
    msgObj[_MESSAGE_TYPE] = 'call';
    msgObj[_CALLBACK_ID] = callbackID;
 
    _sendMessage(JSON.stringify(msgObj));
}
 
function _on(event, callback) {
    if (!event || typeof event !== 'string') {
        return;
    };
 
    if (typeof callback !== 'function') {
        return;
    };
 
    _event_hook_map[event] = callback;
}
 
function _emit(event, argv) {
    if (typeof _event_hook_map[event] !== 'function') {
        return;
    };
    _event_hook_map[event](argv);
}
 
function _enable_old_UrlStyleImagePreviews() {
    var old_prefix = "weixin://viewimage/";
    _WXJS('a[href^="weixin://viewimage/"]').on('click', function (e) {
        var cur = '';
        var link;
        if (typeof e.target.href === 'string' && e.target.href.search(old_prefix) === 0) {
            link = e.target;
        } else {
            link = _WXJS(e.target).parents('a[href^="weixin://viewimage/"]')[0];
        }
        cur = link.href.substr(old_prefix.length);
        var allLinks = _WXJS('a[href^="weixin://viewimage/"]');
        var allUrls = [];
        for (var i = 0; i < allLinks.length; i++) {
            allUrls.push(allLinks[i].href.substr(old_prefix.length));
        };
        _call('imagePreview', {
            'urls': allUrls,
            'current': cur
        });
 
        e.preventDefault();
    });
}
 
function _enable_old_ReaderShareUrls() {
    var old_prefix = "weixin://readershare/";
    _WXJS('a[href^="weixin://readershare/"]').on('click', function (e) {
        e.preventDefault();
 
        _emit('menu:share:weibo', _session_data.shareWeiboData || {});
    });
    _WXJS('a[href^="weixin://readertimeline/"]').on('click', function (e) {
        e.preventDefault();
 
        _emit('menu:share:timeline', _session_data.shareTimelineData || {});
    });
}
 
function _enable_hashChangeNotify() {
    _WXJS(window).bind('hashchange', function () {
        _call('hashChange', {
            'hash': window.location.hash
        });
    });
}
 
function _setDefaultEventHandlers() {
    // set font
    _on('menu:setfont', function (argv) {
        if (typeof changefont === 'function') {
            var num = parseInt(argv.fontSize);
            _log('set font size with changefont: %s', argv.fontSize);
            changefont(num);
            return;
        }
 
        // fallback
        var s;
        switch (argv.fontSize) {
        case '1':
            s = '80%';
            break;
        case '2':
            s = '100%';
            break;
        case '3':
            s = '120%';
            break;
        case '4':
            s = '140%';
            break;
        default:
            return;
        }
        _log('set font size with webkitTextSizeAdjust: %s', argv.fontSize);
 
        // document.getElementsByTagName('body')[0].style.webkitTextSizeAdjust = s;
        // android机器上调用该方法不生效，改为回调到java层，用webview的api实现setFontSize
        _call('setFontSizeCallback', {
            "fontSize": argv.fontSize
        });
    });
 
    // 获取页面图片算法：
    // 在页面中找到第一个最小边大于290的图片，如果1秒内找不到，则返回空（不带图分享）。
    var getSharePreviewImage = function (cb) {
 
        var isCalled = false;
 
        var callCB = function (_img) {
            if (isCalled) {
                return;
            };
            isCalled = true;
 
            cb(_img);
        }
 
        var _allImgs = _WXJS('img');
        if (_allImgs.length == 0) {
            return callCB();
        }
 
        // 过滤掉重复的图片
        var _srcs = {};
        var allImgs = [];
        for (var i = 0; i < _allImgs.length; i++) {
            var _img = _allImgs[i];
 
            // 过滤掉不可以见的图片
            if (_WXJS(_img).css('display') == 'none' || _WXJS(_img).css('visibility') == 'hidden') {
                // _log('ivisable image !! ' + _img.src);
                continue;
            }
 
            if (_srcs[_img.src]) {
                // added
            } else {
                _srcs[_img.src] = 1; // mark added
                allImgs.push(_img);
            }
        };
 
        var results = [];
 
        var img;
        for (var i = 0; i < allImgs.length && i < 100; i++) {
            img = allImgs[i];
 
            var newImg = new Image();
            newImg.onload = function () {
                this.isLoaded = true;
                var loadedCount = 0;
                for (var j = 0; j < results.length; j++) {
                    var res = results[j];
                    if (!res.isLoaded) {
                        break;
                    }
                    loadedCount++;
                    if (res.width > 290 && res.height > 290) {
                        callCB(res);
                        break;
                    }
                }
                if (loadedCount == results.length) {
                    // 全部都已经加载完了，但还是没有找到。
                    callCB();
                };
            }
            newImg.src = img.src;
            results.push(newImg);
        }
 
        setTimeout(function () {
            for (var j = 0; j < results.length; j++) {
                var res = results[j];
                if (!res.isLoaded) {
                    continue;
                }
                if (res.width > 290 && res.height > 290) {
                    callCB(res);
                    return;
                }
            }
            callCB();
        }, 1000);
    }
 
    // share timeline
    _on('menu:share:timeline', function (argv) {
        _log('share timeline');
 
        var data;
        if (typeof argv.title === 'string') {
            data = argv;
            _call('shareTimeline', data);
        } else {
            data = {
                // "img_url": "",
                // "img_width": "",
                // "img_height": "",
                "link": document.documentURI || _session_data.init_url,
                "desc": document.documentURI || _session_data.init_url,
                "title": document.title
            };
 
            var shareFunc = function (_img) {
                if (_img) {
                    data['img_url'] = _img.src;
                    data['img_width'] = _img.width;
                    data['img_height'] = _img.height;
                }
 
                _call('shareTimeline', data);
            };
 
            getSharePreviewImage(shareFunc);
        }
    });
 
    // share weibo
    _on('menu:share:weibo', function (argv) {
        _log('share weibo');
 
        var data;
        if (typeof argv.content === 'string') {
            data = argv;
        } else {
            data = {
                "url": document.documentURI || _session_data.short_url || _session_data.init_url,
                "content": document.title + ' ' + (document.documentURI || _session_data.short_url || _session_data.init_url)
            };
        }
 
        _call('shareWeibo', data);
    });
 
    // share with app message
    _on('menu:share:appmessage', function (argv) {
        _log('share appmessage');
 
        var data;
        if (typeof argv.title === 'string') {
            data = argv;
            _call('sendAppMessage', data);
        } else {
            data = {
                // "appid" : ""
                // "img_url": "",
                // "img_width": "",
                // "img_height": "",
                "link": document.documentURI || _session_data.init_url,
                "desc": document.documentURI || _session_data.init_url,
                "title": document.title
            };
 
            var shareFunc = function (_img) {
                if (_img) {
                    data['img_url'] = _img.src;
                    data['img_width'] = _img.width;
                    data['img_height'] = _img.height;
                }
 
                _call('sendAppMessage', data);
            };
 
            getSharePreviewImage(shareFunc);
        }
    });
 
    // send mail
    _on('menu:share:email', function (argv) {
        _log('send email');
 
        var data;
        if (typeof argv.title === 'string') {
            data = argv;
            _call('sendEmail', data);
        } else {
            data = {
                "content": document.documentURI || _session_data.init_url,
                "title": document.title
            };
            _call('sendEmail', data);
        }
    });
 
    // the first event
    _on('sys:init', function (ses) {
        // 避免由于Java层多次发起init请求，造成网页端多次收到WeixinJSBridgeReady事件
        if (window.WeixinJSBridge._hasInit) {
            _log('hasInit, no need to init again');
            return;
        }
 
        window.WeixinJSBridge._hasInit = true;
 
        _session_data = ses;
 
        // bridge ready
        var readyEvent = doc.createEvent('Events');
        readyEvent.initEvent('WeixinJSBridgeReady');
        doc.dispatchEvent(readyEvent);
    });
 
    _on('sys:bridged', function (ses) {
        // 避免由于Java层多次发起init请求，造成网页端多次收到WeixinJSBridgeReady事件
        if (window.WeixinJSBridge._hasInit) {
            return;
        }
 
        // _log(_env('version'));
        // _log(_env('language'));
        // _log(_env('timezone'));
        // _log(_env('cpu'));
        // _log(_env('model'));
        if (_env('webview_type') === '1') {
            _emit('menu:setfont', {
                'fontSize': _env('init_font_size')
            });
        }
 
        try {
            _enable_old_UrlStyleImagePreviews();
            _enable_old_ReaderShareUrls();
            _enable_hashChangeNotify();
        } catch (e) {
            _log('error %s', e);
        }
 
    });
}
 
function _test_start() {
    _emit('sys:init', {});
    _emit('sys:bridged', {});
}
 
var __WeixinJSBridge = {
    // public
    invoke: _call,
    call: _call,
    on: _on,
    env: _env,
    log: _log,
    // private
    // _test_start:_test_start,
    _fetchQueue: _fetchQueue,
    _handleMessageFromWeixin: _handleMessageFromWeixin,
    _hasInit: false
};
 
if (window.WeixinJSBridge) {
    _WXJS.extend(window.WeixinJSBridge, __WeixinJSBridge);
} else {
    window.WeixinJSBridge = __WeixinJSBridge;
}
 
var doc = document;
_createQueueReadyIframe(doc);
_setDefaultEventHandlers();
 
//})();