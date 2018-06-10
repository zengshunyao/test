function setElemhideCSSRules(e) {
    function t() {
        if (!n.sheet)return void window.setTimeout(t, 0);
        for (var o = 0, i = 0; o < e.length; o += SELECTOR_GROUP_SIZE, i++) {
            var l = e.slice(o, o + SELECTOR_GROUP_SIZE).join(", ");
            n.sheet.insertRule(l + " { display: none !important; }", i)
        }
    }

    if (elemhideElt && elemhideElt.parentNode && elemhideElt.parentNode.removeChild(elemhideElt), e) {
        elemhideElt = document.createElement("style"), elemhideElt.setAttribute("type", "text/css"), elemhideElt.setAttribute("adt", "123"), (document.head || document.documentElement).appendChild(elemhideElt);
        var n = elemhideElt;
        t()
    }
}
function checkCollapse(e) {
    var t = e.target, n = t.localName, o = "iframe" == n || "frame" == n ? "load" : "error";
    if (n in typeMap && e.type == o) {
        var i = t.src;
        if (!i)return;
        var l = typeMap[n];
        chrome.extension.sendRequest({
            reqtype: "should-collapse",
            url: i,
            documentUrl: document.URL,
            type: l
        }, function (e) {
            e && t.parentNode && ("frame" == n ? t.style.setProperty("visibility", "hidden", "!important") : t.parentNode.removeChild(t))
        })
    }
}
function init() {
    document.documentElement instanceof HTMLElement && (document.addEventListener("error", checkCollapse, !0), document.addEventListener("load", checkCollapse, !0), chrome.extension.sendRequest({
        reqtype: "get-settings",
        selectors: !0,
        frameUrl: window.location.href
    }, function (e) {
        setElemhideCSSRules(e.selectors)
    }))
}
function checkpage() {
    if (document.documentElement instanceof HTMLElement) {
        var e = !1;
        if (null != document.head)
            for (var t = document.head.getElementsByTagName("style"), n = 0, o = t.length; n < o; n++)
                if ("123" == t[n].getAttribute("adt")) {
            e = !0;
            break
        }
        e || chrome.extension.sendRequest({
            reqtype: "get-settings",
            selectors: !0,
            frameUrl: window.location.href
        }, function (e) {
            var t = e.selectors;
            if (t && e.enabled) {
                console.log("反屏蔽");
                var n = document.createElement("style");
                n.setAttribute("type", "text/css"), n.setAttribute("adt", "123"), (document.head || document.documentElement).appendChild(n);
                for (var o = 0, i = 0; o < t.length; o += SELECTOR_GROUP_SIZE, i++) {
                    var l = t.slice(o, o + SELECTOR_GROUP_SIZE).join(", ");
                    n.sheet.insertRule(l + " { display: none !important; }", i)
                }
            }
        }), window.setTimeout(checkpage, 100)
    }
}
var SELECTOR_GROUP_SIZE = 20, elemhideElt = null, typeMap = {
    img: "IMAGE",
    input: "IMAGE",
    audio: "MEDIA",
    video: "MEDIA",
    frame: "SUBDOCUMENT",
    iframe: "SUBDOCUMENT"
};
document.documentElement ? init() : window.setTimeout(init, 0), (document.URL.indexOf("www.baidu.com") > 0 || document.URL.indexOf("www.google.com") > 0) && checkpage();