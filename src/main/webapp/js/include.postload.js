function checkpage() {
    if (document.documentElement instanceof HTMLElement) {
        for (var e = document.head.getElementsByTagName("style"), t = !1, i = 0, l = e.length; i < l; i++)if ("123" == e[i].getAttribute("adt")) {
            t = !0;
            break
        }
        t || chrome.extension.sendRequest({
            reqtype: "get-settings",
            selectors: !0,
            frameUrl: window.location.href
        }, function (e) {
            var t = e.selectors;
            if (t && e.enabled) {
                var i = document.createElement("style");
                i.setAttribute("type", "text/css"), i.setAttribute("adt", "123"), (document.head || document.documentElement).appendChild(i);
                for (var l = 0, r = 0; l < t.length; l += SELECTOR_GROUP_SIZE, r++) {
                    var c = t.slice(l, l + SELECTOR_GROUP_SIZE).join(", ");
                    i.sheet.insertRule(c + " { display: none !important; }", r)
                }
            }
        }), window.setTimeout(checkpage, 100)
    }
}
function highlightElements(e) {
    highlightedElementsSelector && unhighlightElements();
    var t = document.querySelectorAll(e);
    highlightedElementsSelector = e, highlightedElementsBoxShadows = new Array, highlightedElementsBGColors = new Array;
    for (var i = 0; i < t.length; i++)highlightedElementsBoxShadows[i] = t[i].style.getPropertyValue("-webkit-box-shadow"), highlightedElementsBGColors[i] = t[i].style.backgroundColor, t[i].style.setProperty("-webkit-box-shadow", "inset 0px 0px 5px #fd6738"), t[i].style.backgroundColor = "#f6e1e5"
}
function unhighlightElements() {
    if (null != highlightedElementsSelector) {
        for (var e = document.querySelectorAll(highlightedElementsSelector), t = 0; t < e.length; t++)e[t].style.setProperty("-webkit-box-shadow", highlightedElementsBoxShadows[t]), e[t].style.backgroundColor = highlightedElementsBGColors[t];
        highlightedElementsSelector = null
    }
}
function getAbsolutePosition(e) {
    for (var t = 0, i = 0; e; e = e.offsetParent)t += e.offsetLeft, i += e.offsetTop;
    return [t, i]
}
function addElementOverlay(e) {
    if (!e)return null;
    var t = getElementURL(e);
    if (e.className || e.id || t) {
        var i = getComputedStyle(e, null), l = document.createElement("div");
        l.prisoner = e, l.prisonerURL = t, l.className = "__adblockplus__overlay", l.setAttribute("style", "opacity:0.4; background-color:#ffffff; display:inline-box; width:" + i.width + "; height:" + i.height + "; position:absolute; overflow:hidden; -webkit-box-sizing:border-box; z-index: 99999");
        var r = getAbsolutePosition(e);
        return l.style.left = r[0] + "px", l.style.top = r[1] + "px", document.body.appendChild(l), l
    }
}
function clickHide_showDialog(e, t, i) {
    if (clickHide_activated || clickHideFiltersDialog) {
        var l = currentElement.prisoner ? currentElement.prisoner : currentElement;
        clickHide_deactivate(), currentElement = l
    }
    clickHide_filters = i, clickHideFiltersDialog = document.createElement("iframe"), clickHideFiltersDialog.src = chrome.extension.getURL("block.html"), clickHideFiltersDialog.setAttribute("style", "position: fixed !important; visibility: hidden; display: block !important; border: 0px !important;"), clickHideFiltersDialog.style.WebkitBoxShadow = "5px 5px 20px rgba(0,0,0,0.5)", clickHideFiltersDialog.style.zIndex = 99999, clickHideFiltersDialog.style.left = "50px", clickHideFiltersDialog.style.top = "50px", clickHideFiltersDialog.onmouseout = function () {
        clickHideFiltersDialog && clickHideFiltersDialog.style.setProperty("opacity", "0.8")
    }, clickHideFiltersDialog.onmouseover = function () {
        clickHideFiltersDialog && clickHideFiltersDialog.style.setProperty("opacity", "1.0")
    }, document.body.appendChild(clickHideFiltersDialog)
}
function clickHide_activate() {
    if (null != document) {
        (clickHide_activated || clickHideFiltersDialog) && clickHide_deactivate();
        for (var e = document.querySelectorAll("object,embed,img,iframe"), t = 0; t < e.length; t++)
            addElementOverlay(e[t]);
        clickHide_activated = !0, document.addEventListener("mouseover", clickHide_mouseOver, !1), document.addEventListener("mouseout", clickHide_mouseOut, !1), document.addEventListener("click", clickHide_mouseClick, !1), document.addEventListener("keyup", clickHide_keyUp, !1)
    }
}
function clickHide_rulesPending() {
    clickHide_activated = !1, document.removeEventListener("mouseover", clickHide_mouseOver, !1), document.removeEventListener("mouseout", clickHide_mouseOut, !1), document.removeEventListener("click", clickHide_mouseClick, !1), document.removeEventListener("keyup", clickHide_keyUp, !1)
}
function clickHide_deactivate() {
    if (clickHideFiltersDialog && (document.body.removeChild(clickHideFiltersDialog), clickHideFiltersDialog = null), currentElement && (currentElement.removeEventListener("contextmenu", clickHide_elementClickHandler, !1), unhighlightElements(), currentElement.style.setProperty("-webkit-box-shadow", currentElement_boxShadow), currentElement.style.backgroundColor = currentElement_backgroundColor, currentElement = null, clickHideFilters = null), unhighlightElements(), clickHide_activated = !1, clickHide_filters = null, document) {
        document.removeEventListener("mouseover", clickHide_mouseOver, !1), document.removeEventListener("mouseout", clickHide_mouseOut, !1), document.removeEventListener("click", clickHide_mouseClick, !1), document.removeEventListener("keyup", clickHide_keyUp, !1);
        for (var e; e = document.querySelector(".__adblockplus__overlay");)e.parentNode.removeChild(e)
    }
}
function clickHide_elementClickHandler(e) {
    e.preventDefault(), e.stopPropagation(), clickHide_mouseClick(e)
}
function clickHide_mouseOver(e) {
    0 != clickHide_activated && (e.target.id || e.target.className || e.target.src) && (currentElement = e.target, currentElement_boxShadow = e.target.style.getPropertyValue("-webkit-box-shadow"), currentElement_backgroundColor = e.target.style.backgroundColor, e.target.style.setProperty("-webkit-box-shadow", "inset 0px 0px 5px #d6d84b"), e.target.style.backgroundColor = "#f8fa47", e.target.addEventListener("contextmenu", clickHide_elementClickHandler, !1))
}
function clickHide_mouseOut(e) {
    clickHide_activated && currentElement && (currentElement.style.setProperty("-webkit-box-shadow", currentElement_boxShadow), currentElement.style.backgroundColor = currentElement_backgroundColor, currentElement.removeEventListener("contextmenu", clickHide_elementClickHandler, !1))
}
function clickHide_keyUp(e) {
    e.ctrlKey && e.shiftKey && 69 == e.keyCode && clickHide_mouseClick(e)
}
function clickHide_mouseClick(e) {
    if (currentElement && clickHide_activated) {
        var t = currentElement, i = null;
        currentElement.className && "__adblockplus__overlay" == currentElement.className ? (t = currentElement.prisoner, i = currentElement.prisonerURL) : t.src && (i = t.src), i && (i = normalizeURL(relativeToAbsoluteUrl(i)));
        var l = t.id ? t.id.split(" ").join("") : null, r = null;
        if (t.className && (r = t.className.replace(/\s+/g, " ").replace(/^\s+|\s+$/g, "").split(" ")), clickHideFilters = new Array, selectorList = new Array, l && (clickHideFilters.push(document.domain + "###" + l), selectorList.push("#" + l)), r)for (var c = 0; c < r.length; c++)clickHideFilters.push(document.domain + "##." + r[c]), selectorList.push("." + r[c]);
        i && (clickHideFilters.push(relativeToAbsoluteUrl(i)), selectorList.push(t.localName + '[src="' + i + '"]')), clickHide_showDialog(e.clientX, e.clientY, clickHideFilters), currentElement.style.setProperty("-webkit-box-shadow", currentElement_boxShadow), currentElement.style.backgroundColor = currentElement_backgroundColor, highlightElements(selectorList.join(",")), currentElement.style.setProperty("-webkit-box-shadow", "inset 0px 0px 5px #fd1708"), currentElement.style.backgroundColor = "#f6a1b5"
    }
}
function getElementURL(e) {
    var t;
    if ("OBJECT" != e.localName.toUpperCase() || (t = e.getAttribute("data")))t || (t = e.getAttribute("src") || e.getAttribute("href")); else {
        var i = e.querySelectorAll('param[name="movie"]');
        i[0] ? t = i[0].getAttribute("value") : (i = e.querySelectorAll('param[name="src"]'), i[0] && (t = i[0].getAttribute("value")))
    }
    return t
}
function relativeToAbsoluteUrl(e) {
    if (!e || /^[\w\-]+:/i.test(e))return e;
    if ("/" == e[0])return "/" == e[1] ? document.location.protocol + e : document.location.protocol + "//" + document.location.host + e;
    var t = document.baseURI.match(/.+\//);
    return t ? t[0] + e : document.baseURI + "/" + e
}
function removeDotSegments(e) {
    var t = "", i = [];
    if (/\./.test(e)) {
        for (; void 0 !== e && "" !== e;)"." === e || ".." === e ? e = "" : /^\.\.\//.test(e) ? e = e.substring(3) : /^\.\//.test(e) ? e = e.substring(2) : /^\/\.(\/|$)/.test(e) ? e = "/" + e.substring(3) : /^\/\.\.(\/|$)/.test(e) ? (e = "/" + e.substring(4), t = t.replace(/\/?[^\/]+$/, "")) : (i = e.match(/^(\/?[^\/]*)(\/.*)?$/), e = i[2], t += i[1]);
        return t
    }
    return e
}
function normalizeURL(e) {
    var t = e.match(/(.+:\/\/.+?)\/(.*)/);
    if (!t)return e;
    var i = removeDotSegments(t[2]);
    return 0 == i.length ? t[1] : ("/" != i[0] && (i = "/" + i), t[1] + i)
}
function showVideoWarn() {
    var e = document.createElement("div");
    e.setAttribute("id", "adt-warn-video"), e.innerHTML = '<div style="background-color: #000;opacity: .4;position: absolute;z-index: 9998;width: 100%;height: 100%;left: 0;top: 0;"></div><div style="width: 400px;left:50%;top:200px;margin-left:-200px;height: 200px;background-color: #eaeaec;border-radius: 8px;position:fixed;z-index: 9999;overflow: hidden;"><div style="padding:30px;text-align:left;font: 16px/18px arial">视频广告过滤功能并不稳定，可能导致视频无法播放。如果视频无法播放请禁用该功能，或者参考官方的设置教程。<br><br>相关链接：<a href="http://www.adtchrome.com" target="_blank">官方首页</a>&nbsp;&middot;&nbsp;<a href="http://www.adtchrome.com/help/" target="_blank">帮助支持</a>&nbsp;&middot;&nbsp;<a href="chrome-extension://fpdnjdlbdmifoocedhkighhlbchbiikl/options.html#tab-support" target="_blank">用户交流</a><br><br><label style="cursor:pointer;"><input type="checkbox" id="shouldShowVideoWarn"><span style="font-size:14px;color:#555">不再显示该提示</span></label><div style="text-align: center;"><button type="button" id="close-adt-warn" style="background-color: #428bca;border-color: #357ebd;color: #fff;display: inline-block;margin-bottom: 0;font-weight: 400;border: 1px solid transparent;padding: 6px 12px;font-size: 14px;line-height: 1.4;border-radius: 4px;cursor: pointer;height:33px;">我知道了</button></div></div></div>', e.querySelector("#close-adt-warn").onclick = function () {
        var e = document.getElementById("adt-warn-video");
        e.parentNode.removeChild(e)
    }, e.querySelector("#shouldShowVideoWarn").onclick = function () {
        chrome.extension.sendRequest({
            reqtype: "set-localstorage",
            lparam: "shouldShowVideoWarn",
            lvalue: !this.checked
        }, function (e) {
        })
    }, document.body.appendChild(e)
}
(document.URL.indexOf("www.baidu.com") > 0 || document.URL.indexOf("www.google.com") > 0) && checkpage(), chrome.extension.sendRequest({reqtype: "get-script"}, function (e) {
    if (!(void 0 == e.cscripts || e.cscripts.length <= 0))
        for (var t = 0; t < e.cscripts.length; t++) {
            var i = document.createElement("script");
            i.innerHTML = e.cscripts[t], document.head.appendChild(i)
    }
});
var clickHide_activated = !1, clickHide_filters = null, currentElement = null, currentElement_boxShadow = null, currentElement_backgroundColor, clickHideFilters = null, highlightedElementsSelector = null, highlightedElementsBoxShadows = null, highlightedElementsBGColors = null, clickHideFiltersDialog = null, lastRightClickEvent = null;
document.documentElement instanceof HTMLElement && (document.addEventListener("contextmenu", function (e) {
    lastRightClickEvent = e
}, !1), document.addEventListener("click", function (e) {
    if (2 != e.button) {
        for (var t = e.target; t && !(t instanceof HTMLAnchorElement);)t = t.parentNode;
        if (t && "abp:" == t.protocol) {
            e.preventDefault(), e.stopPropagation();
            var i = t.href;
            if (/^abp:\/*subscribe\/*\?(.*)/i.test(i)) {
                for (var l = RegExp.$1.split("&"), r = null, c = null, o = 0; o < l.length; o++) {
                    var n = l[o].split("=", 2);
                    if (2 == n.length && /\S/.test(n[1]))switch (n[0]) {
                        case"title":
                            r = decodeURIComponent(n[1]);
                            break;
                        case"location":
                            c = decodeURIComponent(n[1])
                    }
                }
                c && (r || (r = c), r = r.replace(/^\s+/, "").replace(/\s+$/, ""), c = c.replace(/^\s+/, "").replace(/\s+$/, ""), /^(https?|ftp):/.test(c) && chrome.extension.sendRequest({
                    reqtype: "add-subscription",
                    title: r,
                    url: c
                }))
            }
        }
    }
}, !0), chrome.extension.onRequest.addListener(function (e, t, i) {
    switch (e.reqtype) {
        case"get-clickhide-state":
            i({active: clickHide_activated});
            break;
        case"clickhide-activate":
            clickHide_activate();
            break;
        case"clickhide-deactivate":
            clickHide_deactivate();
            break;
        case"clickhide-new-filter":
            if (!lastRightClickEvent)return;
            var l = lastRightClickEvent.target, r = l.src;
            if (e.filter !== r)for (var c = document.querySelectorAll("[src]"), o = 0; o < c.length; o++)if (r = c[o].src, e.filter === r) {
                l = c[o];
                break
            }
            e.filter === r ? (clickHide_activated = !0, clickHideFilters = [e.filter], currentElement = addElementOverlay(l), clickHide_mouseClick(lastRightClickEvent)) : console.log("clickhide-new-filter: URLs don't match. Couldn't find that element.", e.filter, r, lastRightClickEvent.target.src);
            break;
        case"clickhide-init":
            clickHideFiltersDialog && (i({filters: clickHide_filters}), clickHideFiltersDialog.style.width = "420px", clickHideFiltersDialog.style.height = "225px", clickHideFiltersDialog.style.visibility = "visible");
            break;
        case"clickhide-move":
            clickHideFiltersDialog && (clickHideFiltersDialog.style.left = parseInt(clickHideFiltersDialog.style.left, 10) + e.x + "px", clickHideFiltersDialog.style.top = parseInt(clickHideFiltersDialog.style.top, 10) + e.y + "px");
            break;
        case"clickhide-close":
            clickHideFiltersDialog && (e.remove && currentElement && currentElement.parentNode && currentElement.parentNode.removeChild(currentElement), clickHide_deactivate());
            break;
        case"show-video-warn":
            showVideoWarn(), i({});
            break;
        default:
            i({})
    }
}));