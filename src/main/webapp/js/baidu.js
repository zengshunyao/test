var define;
var require;
var esl;
(function (b) {
    var o = {};
    var C = {};
    var x = 1;
    var g = 2;
    var s = 3;
    var p = 4;
    var ah = X();
    var N;

    function i(ap, aq) {
        var an = [];

        function am(ar) {
            if (ar.indexOf(".") === 0) {
                an.push(ar)
            }
        }

        if (typeof ap === "string") {
            am(ap)
        } else {
            aa(ap, function (ar) {
                am(ar)
            })
        }
        if (an.length > 0) {
            throw new Error("[REQUIRE_FATAL]Relative ID is not allowed in global require: " + an.join(", "))
        }
        var ao = n.waitSeconds;
        if (ao && (ap instanceof Array)) {
            if (N) {
                clearTimeout(N)
            }
            N = setTimeout(I, ao * 1000)
        }
        return ah(ap, aq)
    }

    i.version = "1.8.6";
    i.loader = "esl";
    i.toUrl = ah.toUrl;
    function I() {
        var aq = [];
        var ar = [];
        var ao = {};
        var ap = {};
        var an = {};

        function am(aw, av) {
            if (an[aw] || l(aw, p)) {
                return
            }
            an[aw] = 1;
            if (!l(aw, s)) {
                if (!ao[aw]) {
                    ao[aw] = 1;
                    aq.push(aw)
                }
            }
            var au = o[aw];
            if (!au) {
                if (!ap[aw]) {
                    ap[aw] = 1;
                    ar.push(aw)
                }
            } else {
                if (av) {
                    if (!ao[aw]) {
                        ao[aw] = 1;
                        aq.push(aw)
                    }
                    aa(au.depMs, function (ax) {
                        am(ax.absId, ax.hard)
                    })
                }
            }
        }

        for (var at in C) {
            am(at, 1)
        }
        if (aq.length || ar.length) {
            throw new Error("[MODULE_TIMEOUT]Hang( " + (aq.join(", ") || "none") + " ) Miss( " + (ar.join(", ") || "none") + " )")
        }
    }

    var z;

    function R(aq, ap, ao) {
        if (ao == null) {
            if (ap == null) {
                ao = aq;
                aq = null
            } else {
                ao = ap;
                ap = null;
                if (aq instanceof Array) {
                    ap = aq;
                    aq = null
                }
            }
        }
        if (ao == null) {
            return
        }
        var am = window.opera;
        if (!aq && document.attachEvent && (!(am && am.toString() === "[object Opera]"))) {
            var an = t();
            aq = an && an.getAttribute("data-require-id")
        }
        if (aq) {
            y(aq, ap, ao);
            if (z) {
                clearTimeout(z)
            }
        } else {
            r[0] = {deps: ap, factory: ao}
        }
    }

    R.amd = {};
    function ad() {
        var am = n.config[this.id];
        if (am && typeof am === "object") {
            return am
        }
        return {}
    }

    function y(ao, an, am) {
        if (!o[ao]) {
            o[ao] = {
                id: ao,
                depsDec: an,
                deps: an || ["require", "exports", "module"],
                factoryDeps: [],
                factory: am,
                exports: {},
                config: ad,
                state: x,
                require: X(ao),
                depMs: [],
                depMkv: {},
                depRs: [],
                depPMs: []
            }
        }
    }

    function ag(ar) {
        var ao = o[ar];
        if (!ao || l(ar, g)) {
            return
        }
        var aq = ao.deps;
        var an = ao.factory;
        var ap = 0;
        if (typeof an === "function") {
            ap = Math.min(an.length, aq.length);
            !ao.depsDec && an.toString().replace(/(\/\*([\s\S]*?)\*\/|([^:]|^)\/\/(.*)$)/mg, "").replace(/require\(\s*(['"'])([^'"]+)\1\s*\)/g, function (au, at, av) {
                aq.push(av)
            })
        }
        var am = [];
        aa(aq, function (au, at) {
            var ax = W(au);
            var aw = af(ax.mod, ar);
            var ay;
            var av;
            if (aw && !B[aw]) {
                if (ax.res) {
                    av = {id: au, mod: aw, res: ax.res};
                    C[aw] = 1;
                    ao.depPMs.push(aw);
                    ao.depRs.push(av)
                }
                ay = ao.depMkv[aw];
                if (!ay) {
                    ay = {id: ax.mod, absId: aw, hard: at < ap};
                    ao.depMs.push(ay);
                    ao.depMkv[aw] = ay;
                    am.push(aw)
                }
            } else {
                ay = {absId: aw}
            }
            if (at < ap) {
                ao.factoryDeps.push(av || ay)
            }
        });
        ao.state = g;
        O(ar);
        a(am)
    }

    function S() {
        for (var am in C) {
            E(am);
            al(am)
        }
    }

    function E(ao) {
        var am = {};
        an(ao);
        function an(ar) {
            if (!l(ar, g)) {
                return false
            }
            if (l(ar, s) || am[ar]) {
                return true
            }
            am[ar] = 1;
            var ap = o[ar];
            var aq = true;
            aa(ap.depMs, function (at) {
                return (aq = an(at.absId))
            });
            aq && aa(ap.depRs, function (at) {
                aq = !!(at.absId && l(at.absId, p));
                return aq
            });
            if (aq) {
                ap.state = s
            }
            return aq
        }
    }

    function O(ap) {
        var an = o[ap];
        var ao;
        an.invokeFactory = am;
        aa(an.depPMs, function (aq) {
            f(aq, function () {
                aa(an.depRs, function (ar) {
                    if (!ar.absId && ar.mod === aq) {
                        ar.absId = af(ar.id, ap);
                        a([ar.absId], S)
                    }
                })
            })
        });
        function am() {
            if (ao || an.state !== s) {
                return
            }
            ao = 1;
            var aw = 1;
            var ax = [];
            aa(an.factoryDeps, function (az) {
                var ay = az.absId;
                if (!B[ay]) {
                    al(ay);
                    if (!l(ay, p)) {
                        aw = 0;
                        return false
                    }
                }
                ax.push(ay)
            });
            if (aw) {
                try {
                    var au = ae(ax, {require: an.require, exports: an.exports, module: an});
                    var at = an.factory;
                    var ar = typeof at === "function" ? at.apply(b, au) : at;
                    if (ar != null) {
                        an.exports = ar
                    }
                    an.invokeFactory = null;
                    delete C[ap]
                } catch (av) {
                    ao = 0;
                    if (/^\[MODULE_MISS\]"([^"]+)/.test(av.message)) {
                        var aq = an.depMkv[RegExp.$1];
                        aq && (aq.hard = 1);
                        return
                    }
                    throw av
                }
                D(ap)
            }
        }
    }

    function l(an, am) {
        return o[an] && o[an].state >= am
    }

    function al(an) {
        var am = o[an];
        if (am && am.invokeFactory) {
            am.invokeFactory()
        }
    }

    function ae(an, ao) {
        var am = [];
        aa(an, function (aq, ap) {
            am[ap] = ao[aq] || u(aq)
        });
        return am
    }

    var V = {};

    function f(ao, an) {
        if (l(ao, p)) {
            an();
            return
        }
        var am = V[ao];
        if (!am) {
            am = V[ao] = []
        }
        am.push(an)
    }

    function D(ap) {
        var ao = V[ap] || [];
        var an = o[ap];
        an.state = p;
        var am = ao.length;
        while (am--) {
            ao[am]()
        }
        ao.length = 0;
        delete V[ap]
    }

    function u(am) {
        if (l(am, p)) {
            return o[am].exports
        }
        return null
    }

    var B = {require: i, exports: 1, module: 1};
    var r = [];

    function q(am) {
        aa(r, function (an) {
            y(am, an.deps, an.factory)
        });
        r.length = 0;
        ag(am)
    }

    function a(ap, ar, am, an) {
        if (typeof ap === "string") {
            al(ap);
            if (!l(ap, p)) {
                throw new Error('[MODULE_MISS]"' + ap + '" is not exists!')
            }
            return u(ap)
        }
        an = an || {};
        var aq = 0;
        if (ap instanceof Array) {
            ao();
            if (!aq) {
                aa(ap, function (at) {
                    if (!(B[at] || l(at, p))) {
                        f(at, ao);
                        if (!an[at]) {
                            (at.indexOf("!") > 0 ? F : j)(at, am)
                        }
                        ag(at)
                    }
                });
                S()
            }
        }
        function ao() {
            if (!aq) {
                var at = 1;
                aa(ap, function (au) {
                    if (!B[au]) {
                        return (at = !!l(au, p))
                    }
                });
                if (at) {
                    aq = 1;
                    (typeof ar === "function") && ar.apply(b, ae(ap, B))
                }
            }
        }
    }

    var P = {};

    function j(an) {
        if (P[an] || o[an]) {
            return
        }
        P[an] = 1;
        var am = document.createElement("script");
        am.setAttribute("data-require-id", an);
        am.src = K(an + ".js");
        am.async = true;
        if (am.readyState) {
            am.onreadystatechange = ao
        } else {
            am.onload = ao
        }
        ak(am);
        function ao() {
            var ap = am.readyState;
            if (typeof ap === "undefined" || /^(loaded|complete)$/.test(ap)) {
                am.onload = am.onreadystatechange = null;
                am = null;
                q(an);
                for (var aq in C) {
                    ag(aq)
                }
                S()
            }
        }
    }

    function F(am, ao) {
        if (o[am]) {
            return
        }
        var ar = W(am);
        var aq = {id: am, state: g};
        o[am] = aq;
        function an(at) {
            aq.exports = at || true;
            D(am)
        }

        an.fromText = function (au, at) {
            C[au] = 1;
            new Function(at)();
            q(au)
        };
        function ap(at) {
            var au = ao ? o[ao].require : ah;
            at.load(ar.res, au, an, ad.call({id: am}))
        }

        ap(u(ar.mod))
    }

    var n = {baseUrl: "./", paths: {}, config: {}, map: {}, packages: [], waitSeconds: 0, noRequests: {}, urlArgs: {}};
    i.config = function (an) {
        function ap(ar) {
            am.push(ar)
        }

        if (an) {
            for (var ao in n) {
                var aq = an[ao];
                var am = n[ao];
                if (aq) {
                    if (ao === "urlArgs" && typeof aq === "string") {
                        n.urlArgs["*"] = aq
                    } else {
                        if (am instanceof Array) {
                            aa(aq, ap)
                        } else {
                            if (typeof am === "object") {
                                for (var ao in aq) {
                                    am[ao] = aq[ao]
                                }
                            } else {
                                n[ao] = aq
                            }
                        }
                    }
                }
            }
            aj()
        }
    };
    aj();
    var L;
    var J;
    var M;
    var ac;
    var ab;

    function H(ao, am) {
        var an = U(ao, 1, am);
        an.sort(m);
        return an
    }

    function aj() {
        n.baseUrl = n.baseUrl.replace(/\/$/, "") + "/";
        L = H(n.paths);
        M = H(n.map, 1);
        aa(M, function (am) {
            am.v = H(am.v)
        });
        J = [];
        aa(n.packages, function (am) {
            var an = am;
            if (typeof am === "string") {
                an = {name: am.split("/")[0], location: am, main: "main"}
            }
            an.location = an.location || an.name;
            an.main = (an.main || "main").replace(/\.js$/i, "");
            an.reg = Y(an.name);
            J.push(an)
        });
        J.sort(m);
        ac = H(n.urlArgs, 1);
        ab = H(n.noRequests);
        aa(ab, function (an) {
            var ao = an.v;
            var am = {};
            an.v = am;
            if (!(ao instanceof Array)) {
                ao = [ao]
            }
            aa(ao, function (ap) {
                am[ap] = 1
            })
        })
    }

    function Z(an, am, ao) {
        aa(am, function (ap) {
            if (ap.reg.test(an)) {
                ao(ap.v, ap.k, ap);
                return false
            }
        })
    }

    function K(ap) {
        var au = /(\.[a-z0-9]+)$/i;
        var an = /(\?[^#]*)$/;
        var ar = "";
        var at = ap;
        var ao = "";
        if (an.test(ap)) {
            ao = RegExp.$1;
            ap = ap.replace(an, "")
        }
        if (au.test(ap)) {
            ar = RegExp.$1;
            at = ap.replace(au, "")
        }
        var am = at;
        var aq;
        Z(at, L, function (aw, av) {
            am = am.replace(av, aw);
            aq = 1
        });
        if (!aq) {
            Z(at, J, function (ax, av, aw) {
                am = am.replace(aw.name, aw.location)
            })
        }
        if (!/^([a-z]{2,10}:\/)?\//i.test(am)) {
            am = n.baseUrl + am
        }
        am += ar + ao;
        Z(at, ac, function (av) {
            am += (am.indexOf("?") > 0 ? "&" : "?") + av
        });
        return am
    }

    function X(am) {
        var ao = {};

        function an(ar, av) {
            if (typeof ar === "string") {
                if (!ao[ar]) {
                    ao[ar] = a(af(ar, am))
                }
                return ao[ar]
            } else {
                if (ar instanceof Array) {
                    var au = [];
                    var ap = [];
                    var at = [];
                    aa(ar, function (az, aw) {
                        var ay = W(az);
                        var ax = af(ay.mod, am);
                        ap.push(ax);
                        C[ax] = 1;
                        if (ay.res) {
                            au.push(ax);
                            at[aw] = null
                        } else {
                            at[aw] = ax
                        }
                    });
                    var aq = {};
                    aa(ap, function (ax) {
                        var aw;
                        Z(ax, ab, function (ay) {
                            aw = ay
                        });
                        if (aw) {
                            if (aw["*"]) {
                                aq[ax] = 1
                            } else {
                                aa(ap, function (ay) {
                                    if (aw[ay]) {
                                        aq[ax] = 1;
                                        return false
                                    }
                                })
                            }
                        }
                    });
                    a(ap, function () {
                        aa(at, function (ax, aw) {
                            if (ax == null) {
                                at[aw] = af(ar[aw], am)
                            }
                        });
                        a(at, av, am)
                    }, am, aq)
                }
            }
        }

        an.toUrl = function (ap) {
            return K(af(ap, am))
        };
        return an
    }

    function af(ar, am) {
        if (!ar) {
            return ""
        }
        am = am || "";
        var ap = W(ar);
        if (!ap) {
            return ar
        }
        var aq = ap.res;
        var ao = k(ap.mod, am);
        aa(J, function (at) {
            var au = at.name;
            if (au === ao) {
                ao = au + "/" + at.main;
                return false
            }
        });
        Z(am, M, function (at) {
            Z(ao, at, function (av, au) {
                ao = ao.replace(au, av)
            })
        });
        if (aq) {
            var an = u(ao);
            aq = an.normalize ? an.normalize(aq, function (at) {
                return af(at, am)
            }) : af(aq, am);
            ao += "!" + aq
        }
        return ao
    }

    function k(an, av) {
        if (an.indexOf(".") === 0) {
            var aw = av.split("/");
            var au = an.split("/");
            var am = aw.length - 1;
            var ao = au.length;
            var ar = 0;
            var ap = 0;
            pathLoop:for (var at = 0; at < ao; at++) {
                var aq = au[at];
                switch (aq) {
                    case"..":
                        if (ar < am) {
                            ar++;
                            ap++
                        } else {
                            break pathLoop
                        }
                        break;
                    case".":
                        ap++;
                        break;
                    default:
                        break pathLoop
                }
            }
            aw.length = am - ar;
            au = au.slice(ap);
            return aw.concat(au).join("/")
        }
        return an
    }

    function W(an) {
        var am = an.split("!");
        if (am[0]) {
            return {mod: am[0], res: am[1]}
        }
        return null
    }

    function U(ar, ap, am) {
        var aq = [];
        for (var an in ar) {
            if (ar.hasOwnProperty(an)) {
                var ao = {k: an, v: ar[an]};
                aq.push(ao);
                if (ap) {
                    ao.reg = an === "*" && am ? /^/ : Y(an)
                }
            }
        }
        return aq
    }

    var Q;
    var w;

    function t() {
        if (Q) {
            return Q
        } else {
            if (w && w.readyState === "interactive") {
                return w
            } else {
                var am = document.getElementsByTagName("script");
                var ao = am.length;
                while (ao--) {
                    var an = am[ao];
                    if (an.readyState === "interactive") {
                        w = an;
                        return an
                    }
                }
            }
        }
    }

    var T = document.getElementsByTagName("head")[0];
    var d = document.getElementsByTagName("base")[0];
    if (d) {
        T = d.parentNode
    }
    function ak(am) {
        Q = am;
        d ? T.insertBefore(am, d) : T.appendChild(am);
        Q = null
    }

    function Y(am) {
        return new RegExp("^" + am + "(/|$)")
    }

    function aa(ap, ao) {
        if (ap instanceof Array) {
            for (var an = 0, am = ap.length; an < am; an++) {
                if (ao(ap[an], an) === false) {
                    break
                }
            }
        }
    }

    function m(an, am) {
        var ap = an.k || an.name;
        var ao = am.k || am.name;
        if (ao === "*") {
            return -1
        }
        if (ap === "*") {
            return 1
        }
        return ao.length - ap.length
    }

    if (!b.define) {
        b.define = R;
        if (!b.require) {
            b.require = i
        }
        b.esl = i
    }
})(this);
define("jquery", [], function () {
    return window.jQuery
});
jQuery && jQuery.extend({
    stringify: function stringify(b) {
        if ("JSON" in window) {
            return JSON.stringify(b)
        }
        var l = typeof(b);
        if (l != "object" || b === null) {
            if (l == "string") {
                b = '"' + b + '"'
            }
            return String(b)
        } else {
            var d = {"\b": "\\b", "\t": "\\t", "\n": "\\n", "\f": "\\f", "\r": "\\r", '"': '\\"', "\\": "\\\\"};

            function i(n) {
                if (/["\\\x00-\x1f]/.test(n)) {
                    n = n.replace(/["\\\x00-\x1f]/g, function (o) {
                        var p = d[o];
                        if (p) {
                            return p
                        }
                        p = o.charCodeAt();
                        return "\\u00" + Math.floor(p / 16).toString(16) + (p % 16).toString(16)
                    })
                }
                return '"' + n + '"'
            }

            function a(s) {
                var o = ["["], p = s.length, n, q, r;
                for (q = 0; q < p; q++) {
                    r = s[q];
                    switch (typeof r) {
                        case"undefined":
                        case"function":
                        case"unknown":
                            break;
                        default:
                            if (n) {
                                o.push(",")
                            }
                            o.push($.stringify(r));
                            n = 1
                    }
                }
                o.push("]");
                return o.join("")
            }

            switch (typeof b) {
                case"undefined":
                    return "undefined";
                case"number":
                    return isFinite(b) ? String(b) : "null";
                case"string":
                    return i(b);
                case"boolean":
                    return String(b);
                default:
                    if (b === null) {
                        return "null"
                    } else {
                        if (b instanceof Array) {
                            return a(b)
                        } else {
                            var m = ["{"], g = $.stringify, f, k;
                            for (var j in b) {
                                if (Object.prototype.hasOwnProperty.call(b, j)) {
                                    k = b[j];
                                    switch (typeof k) {
                                        case"undefined":
                                        case"unknown":
                                        case"function":
                                            break;
                                        default:
                                            if (f) {
                                                m.push(",")
                                            }
                                            f = 1;
                                            m.push(g(j) + ":" + g(k))
                                    }
                                }
                            }
                            m.push("}");
                            return m.join("")
                        }
                    }
            }
        }
    }, format: function (d, a) {
        d = String(d);
        var b = Array.prototype.slice.call(arguments, 1), f = Object.prototype.toString;
        if (b.length) {
            b = b.length == 1 ? (a !== null && (/\[object Array\]|\[object Object\]/.test(f.call(a))) ? a : b) : b;
            return d.replace(/#\{(.+?)\}/g, function (g, j) {
                var i = b[j];
                if ("[object Function]" == f.call(i)) {
                    i = i(j)
                }
                return ("undefined" == typeof i ? "" : i)
            })
        }
        return d
    }, subByte: function (m, b, g) {
        var f = [], k = m.split("");
        g = g || "…";
        for (var j = 0, d = k.length; j < d; j++) {
            if (k[j].charCodeAt(0) > 255) {
                f.push("*")
            }
            f.push(k[j])
        }
        if (b && b > 0 && f.length > b) {
            k = f.join("").substring(0, b - 1).replace(/\*/g, "") + g
        } else {
            return m
        }
        return k
    }, getByteLength: function (j) {
        var d = [], g = j.split("");
        for (var f = 0, b = g.length; f < b; f++) {
            if (g[f].charCodeAt(0) > 255) {
                d.push("*")
            }
            d.push(g[f])
        }
        return d.length
    }, _isValidKey: function (a) {
        return (new RegExp('^[^\\x00-\\x20\\x7f\\(\\)<>@,;:\\\\\\"\\[\\]\\?=\\{\\}\\/\\u0080-\\uffff]+\x24')).test(a)
    }, setCookie: function (d, f, b) {
        f = encodeURIComponent(f);
        if (!jQuery._isValidKey(d)) {
            return
        }
        b = b || {};
        var a = b.expires;
        if ("number" == typeof b.expires) {
            a = new Date();
            a.setTime(a.getTime() + b.expires)
        }
        document.cookie = d + "=" + f + (b.path ? "; path=" + b.path : "") + (a ? "; expires=" + a.toGMTString() : "") + (b.domain ? "; domain=" + b.domain : "") + (b.secure ? "; secure" : "")
    }, getCookie: function (b) {
        var f = "";
        if (jQuery._isValidKey(b)) {
            var d = new RegExp("(^| )" + b + "=([^;]*)(;|\x24)"), a = d.exec(document.cookie);
            if (a) {
                f = a[2] || null;
                if ("string" == typeof f) {
                    f = decodeURIComponent(f);
                    return f
                }
            }
        }
        return null
    }, removeCookie: function (b, a) {
        a = a || {};
        a.expires = new Date(0);
        jQuery.setCookie(b, "", a)
    }, limitWd: function (n, b) {
        if (n === "") {
            return ""
        }
        n = n + "";
        var f = [], j = n.split(""), d = j.length, k = 0, m = b || 255;
        if (d <= parseInt(m / 2)) {
            return n
        }
        for (var g = 0; g < d; g++) {
            if (j[g].charCodeAt(0) > 255) {
                k += 2
            } else {
                k += 1
            }
            if (k === m) {
                j = n.substring(0, g + 1);
                return j
            } else {
                if (k > m) {
                    j = n.substring(0, g);
                    return j
                }
            }
        }
        return n
    }
});
function addEV(d, b, a) {
    if (window.attachEvent) {
        d.attachEvent("on" + b, a)
    } else {
        if (window.addEventListener) {
            d.addEventListener(b, a, false)
        }
    }
}
function _aMC(d) {
    var b = d, a = -1;
    while (b = b.parentNode) {
        a = parseInt(b.getAttribute("id"));
        if (a > 0) {
            return a
        }
    }
}
function al_c(a) {
    while (a.tagName != "TABLE") {
        a = a.parentNode
    }
    return a.getAttribute("id")
}
function al_c2(b, a) {
    while (a--) {
        while ((b = b.parentNode).tagName != "TABLE") {
        }
    }
    return b.getAttribute("id")
}
function c(a) {
    var k = a.p1;
    if (a.fm == "alop" && !("rsv_xpath" in a)) {
        if (k && G(k).getAttribute("srcid") == "6677") {
        } else {
            return true
        }
    }
    if (k && !("p5" in a)) {
        a.p5 = k
    }
    var b = window.document.location.href, g = "", d = "", m = "", f = window["BD_PS_C" + (new Date()).getTime()] = new Image();
    for (v in a) {
        switch (v) {
            case"title":
                d = a[v].replace(/<[^<>]+>/g, "");
                if (d && d.length > 100) {
                    d = d.substring(0, 100)
                }
                d = encodeURIComponent(d);
                break;
            case"mu":
            case"url":
                d = escape(a[v]);
                break;
            default:
                d = a[v]
        }
        g += "&" + v + "=" + d
    }
    if (!("mu" in a)) {
        try {
            if (("p2" in a) && G(a.p1).getAttribute("mu") && a.fm != "pl") {
                m = "&mu=" + escape(G(a.p1).getAttribute("mu"))
            }
        } catch (i) {
        }
    }
    var j = bds.comm.ubsurl + "?q=" + bds.comm.queryEnc + g + m + "&rsv_sid=" + bds.comm.sid + "&cid=" + bds.comm.cid + "&qid=" + bds.comm.queryId + "&t=" + new Date().getTime();
    if (bds.comm.inter) {
        j = j + "&rsv_inter=" + bds.comm.inter
    }
    if (bds && bds.comm && bds.comm.seinfo && bds.comm.seinfo.rsv_pstg) {
        j = j + "&rsv_pstg=" + bds.comm.seinfo.rsv_pstg
    }
    if (bds && bds.comm && bds.comm.resultPage) {
        j = j + "&rsv_iorr=1"
    } else {
        j = j + "&rsv_iorr=0"
    }
    if (bds && bds.comm && bds.comm.tn) {
        j = j + "&rsv_tn=" + bds.comm.tn
    }
    if (bds && bds.comm && bds.comm.indexSid) {
        j += "&rsv_isid=" + bds.comm.indexSid
    }
    if (bds && bds.comm && bds.comm.lastVoiceQuery) {
        j += "&rsv_lavo=" + encodeURIComponent(bds.comm.lastVoiceQuery)
    }
    if (bds && bds.comm && Cookie.get("ispeed")) {
        j += "&rsv_ispeed=" + Cookie.get("ispeed")
    }
    if (/ssl_sample/.test(location.href)) {
        var l = location.href.match(/ssl_sample=[^=&]+/i);
        j += "&rsv_" + l[0]
    }
    if (/ssl_s=/.test(location.href)) {
        var l = location.href.match(/ssl_s=[^=&]+/i);
        j += "&rsv_" + l[0]
    }
    j += "&rsv_ssl=" + (location.protocol === "https:" ? 1 : 0);
    j += "&path=" + encodeURIComponent(b);
    f.src = j;
    return true
}
$(window).on("resize", function () {
    if ("pageState" in window && pageState != 0) {
        bds.util.setContainerWidth();
        bds.event.trigger("se.window_resize")
    }
});
(function () {
    var b = bds.util && bds.util.domain ? bds.util.domain.get("http://s1.bdstatic.com") : "http://s1.bdstatic.com";
    var a = bds.util && bds.util.domain ? bds.util.domain.get("http://ecma.bdimg.com") : "http://ecma.bdimg.com";
    require.config({
        baseUrl: b + "/r/www/cache/biz",
        packages: [{name: "ecma", location: a + "/public01"}],
        paths: {
            aladdin: b + "/r/www/aladdin",
            ui: b + "/r/www/cache/amd/ui",
            "ui/config": b + "/r/www/cache/amd/ui/Control",
            "ui/lib": b + "/r/www/cache/amd/ui/Control",
            "ui/Control": b + "/r/www/cache/amd/ui/Control"
        },
        urlArgs: {
            "ui/ImgZoomHover": "20141104",
            "ui/ImgZoomHover1": "20141104",
            "ui/ImgZoomHover2": "20141104",
            "ui/ImgZoomHover3": "20141104"
        }
    })
})();
function TagQ(a, b) {
    return b.getElementsByTagName(a)
}
function h(b) {
    b.style.behavior = "url(#default#homepage)";
    b.setHomePage(bds.comm.domain);
    var a = window["BD_PS_C" + (new Date()).getTime()] = new Image();
    a.src = bds.comm.ubsurl + "?fm=hp&tn=" + bds.comm.tn + "&t=" + new Date().getTime()
}
function setHeadUrl(b) {
    var d = G("kw").value;
    d = encodeURIComponent(d);
    var a = b.href;
    a = a.replace(new RegExp("(" + b.getAttribute("wdfield") + "=)[^&]*"), "\x241" + d);
    b.href = a
}
bds.util.addStyle = function (b) {
    if (isIE) {
        var d = document.createStyleSheet();
        d.cssText = b
    } else {
        var a = document.createElement("style");
        a.type = "text/css";
        a.appendChild(document.createTextNode(b));
        document.getElementsByTagName("HEAD")[0].appendChild(a)
    }
};
bds.util.getContentRightHeight = function () {
    return ($("#content_right").get(0)) ? $("#content_right").get(0).offsetHeight : 0
};
bds.util.getContentLeftHeight = function () {
    return ($("#content_left").get(0)) ? $("#content_left").get(0).offsetHeight : 0
};
if (!window.A) {
    function G(a) {
        return document.getElementById(a)
    }

    window.bds = window.bds || {};
    bds.util = bds.util || {};
    bds.util.getWinWidth = function () {
        return window.document.documentElement.clientWidth
    };
    bds.util.setContainerWidth = function () {
        var g = G("container"), b = G("wrapper"), a = function (i, j) {
            j.className = j.className.replace(i, "")
        }, f = function (i, j) {
            j.className = (j.className + " " + i).replace(/^\s+/g, "")
        }, d = function (i, j) {
            return i.test(j.className)
        };
        if (bds.util.getWinWidth() < 1207) {
            if (g) {
                a(/\bcontainer_l\b/g, g);
                if (!d(/\bcontainer_s\b/, g)) {
                    f("container_s", g)
                }
            }
            if (b) {
                a(/\bwrapper_l\b/g, b);
                if (!d(/\bwrapper_s\b/, b)) {
                    f("wrapper_s", b)
                }
            }
            bds.comm.containerSize = "s"
        } else {
            if (g) {
                a(/\bcontainer_s\b/g, g);
                if (!d(/\bcontainer_l\b/, g)) {
                    f("container_l", g)
                }
            }
            if (b) {
                a(/\bwrapper_s\b/g, b);
                if (!d(/\bwrapper_l\b/, b)) {
                    f("wrapper_l", b)
                }
            }
            bds.comm.containerSize = "l"
        }
    };
    (function () {
        var d = [], i = false;
        var b = function (k, j) {
            try {
                k.call(j)
            } catch (l) {
            }
        }, f = function () {
            this.ids = [];
            this.has = true;
            this.list = [];
            this.logs = [];
            this.loadTimes = [];
            this.groupData = [];
            this.mergeFns = [];
            this._currentContainer = null
        };
        window.A = bds.aladdin = {};
        b(f, window.A);
        bds.ready = function (j) {
            if (typeof j != "function") {
                return
            }
            if (i) {
                b(j)
            } else {
                d.push(j)
            }
        };
        bds.doReady = function () {
            i = true;
            while (d.length) {
                b(d.shift())
            }
        };
        bds.clearReady = function () {
            i = false;
            d = []
        };
        A.__reset = f;
        var a = (function () {
            var j = document.getElementsByTagName("script");
            return function () {
                var l = j[j.length - 1];
                if (window.currentScriptElem) {
                    l = window.currentScriptElem
                }
                var k = l;
                while (k) {
                    if (k.className) {
                        if (/(?:^|\s)result(?:-op)?(?:$|\s)/.test(k.className)) {
                            if (tplname = k.getAttribute("tpl")) {
                                return k
                            }
                        }
                    }
                    k = k.parentNode
                }
            }
        })(), g = function (j, m, l) {
            var n;
            if (!j.initIndex) {
                n = {container: j, data: {}, handlers: []};
                j.initIndex = A.groupData.length + 1;
                A.groupData.push(n)
            } else {
                n = A.groupData[j.initIndex - 1]
            }
            if (typeof m == "function") {
                n.handlers.push(m)
            } else {
                if (typeof m == "object") {
                    for (var o in m) {
                        if (m.hasOwnProperty(o)) {
                            n.data[o] = m[o]
                        }
                    }
                } else {
                    n.data[m] = l
                }
            }
        };
        A.init = A.setup = function (m, l) {
            if (m === undefined || m === null) {
                return
            }
            var j = A._currentContainer || a();
            if (!j) {
                return
            }
            g(j, m, l)
        };
        A.merge = function (k, j) {
            A.mergeFns.push({tplName: k, fn: j})
        }
    })()
}
function ns_c_pj(a, d) {
    var b = encodeURIComponent(window.document.location.href), k = "", i = "", l = "", g = bds.comm.queryEnc, f = bds && bds.util && bds.util.domain ? bds.util.domain.get("http://nsclick.baidu.com") : "http://nsclick.baidu.com", j = window["BD_PS_C" + (new Date()).getTime()] = new Image();
    for (v in a) {
        switch (v) {
            case"title":
                i = encodeURIComponent(a[v].replace(/<[^<>]+>/g, ""));
                break;
            case"url":
                i = encodeURIComponent(a[v]);
                break;
            default:
                i = a[v]
        }
        k += v + "=" + i + "&"
    }
    l = "&mu=" + b;
    j.src = f + "/v.gif?pid=201&" + (d || "") + k + "path=" + b + "&wd=" + g + "&rsv_sid=" + (bds.comm.ishome && bds.comm.indexSid ? bds.comm.indexSid : bds.comm.sid) + "&t=" + new Date().getTime();
    return true
}
function ns_c(b, a) {
    if (a === true) {
        return ns_c_pj(b, "pj=www&rsv_sample=1&")
    }
    return ns_c_pj(b, "pj=www&")
}
A.uiPrefix = "//www.baidu.com/cache/aladdin/ui/";
(function () {
    var b = window.bds.aladdin;
    var g = [];
    var l = {}, i = 0;
    var d = function (q, p) {
        try {
            q.call(p)
        } catch (r) {
        }
    };
    var f = function (p) {
        p.ajaxId = ++i;
        l[p.ajaxId] = p
    };
    var n = function (p) {
        delete l[p.ajaxId]
    };
    var k = function (p) {
        if (!p.ajaxId) {
            return false
        }
        return l.hasOwnProperty(p.ajaxId)
    };
    var m = function (q) {
        var p = {};
        if (q) {
            try {
                var s = new Function("return " + q)();
                if (s) {
                    p = s
                }
            } catch (t) {
            }
        }
        return p
    }, a = function () {
        var r = $(".result-op").get().concat($(".result").get()), t = {};
        for (var q = 0, p, s; s = r[q]; q++) {
            if (p = s.getAttribute("tpl")) {
                if (t[p]) {
                    t[p].push(s)
                } else {
                    t[p] = [s]
                }
            }
        }
        return t
    };
    var o = [], j = [];
    b.addDisposeHandler = function (p) {
        j.push(p)
    };
    b.dispose = function () {
        while (o.length) {
            var r = o.shift();
            d(r.fn, r.obj)
        }
        var p = j;
        for (var q = 0; q < p.length; q++) {
            var r = p[q];
            d(r.fn, r.obj)
        }
    };
    b.__clearDispose = function () {
        o = [];
        j = []
    };
    b.addDisposeHandler({
        obj: l, fn: function () {
            for (var p in l) {
                if (l.hasOwnProperty(p)) {
                    delete l[p]
                }
            }
        }
    });
    b._Aladdin = function () {
        this.p1 = 0;
        this.mu = null
    };
    $.extend(b._Aladdin.prototype, {
        _init: function () {
            var r = this, p;
            p = r.container;
            var q = m(r.container.getAttribute("data-click"));
            r.p1 = q.p1 || p.id;
            r.mu = q.mu || p.getAttribute("mu");
            r.srcid = q.rsv_srcid || p.getAttribute("srcid")
        }, q: function (q, p) {
            p = p || "";
            return $(this.container).find(p + "." + q).get()
        }, qq: function (q, p) {
            return this.q(q, p)[0]
        }, find: function (p) {
            return window.jQuery(p, this.container)
        }, ready: function () {
            $(document).ready.apply(this, arguments)
        }, ajax: function (C, F, u) {
            var D = b.AJAX;
            var H = +new Date();
            var s = u.params || {};
            var w = {query: C, co: u.co || "", resource_id: F, t: H};
            $.extend(w, D.PARAMS);
            $.extend(w, s);
            var C = $.param(w);
            var r = D.API_URL + "?" + C;
            var B = function () {
                var p = new Image();
                p.src = $.format(D.ERR_URL, {url: r});
                b.logs.push(p)
            };
            var z = new Date().getTime();
            var y = function (p) {
                var t = new Date().getTime() - z;
                var I = {fm: "opendataajax", srcid: F, time: t, status: p};
                ns_c(I)
            };
            var E = function (p) {
                if (!k(E)) {
                    return
                }
                q();
                if (p.status == 0) {
                    u.success(p.data)
                } else {
                    u.error && u.error(p.status);
                    B()
                }
                y(0)
            };
            var x = function () {
                if (!k(x)) {
                    return
                }
                q();
                u.timeout && u.timeout();
                B();
                y(1)
            };
            var q = function () {
                n(E);
                n(x)
            };
            f(E);
            f(x);
            $.ajax({
                url: r,
                scriptCharset: D.PARAMS.oe,
                timeout: D.TIMEOUT,
                dataType: "jsonp",
                jsonp: "cb",
                success: E,
                error: x
            })
        }
    });
    b.AJAX = {
        API_URL: bds.util.domain && bds.util.domain.get ? bds.util.domain.get("http://opendata.baidu.com/api.php") : "http://opendata.baidu.com/api.php",
        ERR_URL: bds.util.domain && bds.util.domain.get ? bds.util.domain.get("http://open.baidu.com/stat/al_e.gif?ajax_err_url=#{url}") : "http://open.baidu.com/stat/al_e.gif?ajax_err_url=#{url}",
        PARAMS: {ie: "utf8", oe: "gbk", cb: "op_aladdin_callback", format: "json", tn: "baidu"},
        TIMEOUT: 6000
    };
    g.push(function (p) {
        var q = /msie (\d+\.\d+)/i.test(navigator.userAgent) ? (document.documentMode || +RegExp["\x241"]) : undefined;
        if (q) {
            var r = document.charset;
            $.each(p.container.getElementsByTagName("form"), function (t, u) {
                var w = function () {
                    var x = u.acceptCharset;
                    if (x && x.toString().toUpperCase() != "UNKNOWN" && x != document.charset) {
                        document.charset = x;
                        setTimeout(function () {
                            document.charset = r
                        }, 1000)
                    }
                };
                $(u).on("submit", w);
                var s = u.submit;
                u.submit = function () {
                    w();
                    try {
                        s.call(u)
                    } catch (x) {
                        s()
                    }
                }
            })
        }
    });
    b.__runAla = function () {
        var p = a();
        $.each(b.mergeFns, function (s, q) {
            var r = p[q.tplName];
            if (r) {
                $.each(r, function (t, u) {
                    b._currentContainer = u;
                    q.fn();
                    b._currentContainer = null
                })
            }
        });
        $.each(b.groupData, function (t, s) {
            var w = new b._Aladdin(), r, u, x;
            w.container = s.container;
            w.data = s.data;
            w._init();
            b.list.push(w);
            var q = s.handlers;
            r = new Date();
            while (q.length) {
                d(q.shift(), w)
            }
            if (typeof w.dispose == "function") {
                o.push({obj: w, fn: w.dispose});
                w.dispose = null
            }
            u = new Date(), x = {srcid: w.srcid};
            x.tpl = w.container.getAttribute("tpl");
            x.time = u - r;
            b.loadTimes.push(x);
            $.each(g, function (y, z) {
                z.call(w, w)
            })
        })
    }
})();
(function () {
    var g = window.A, b = {}, j = {}, s = {}, p = document, n = p.getElementsByTagName("head")[0], i = false, f = ["baidu"], q = false, d = g.baidu, m = function () {
    };
    var l = {
        "*": function (t, u) {
            return u + "?v=2014010100"
        }, scrollbarv: function (t, u) {
            return u + "?v=20150226"
        }, likeshare4: function (t, u) {
            return u + "?v=20140116"
        }, mboxsingleton: function (t, u) {
            return u + "?v=20141008"
        }, sms: function (t, u) {
            return u + "?v=20140508"
        }, tab: function (t, u) {
            return u + "?v=20140117"
        }, tabs: function (t, u) {
            return u + "?v=20140116"
        }, musicplayer: function (t, u) {
            return u + "?v=20150310"
        }, slider: function (t, u) {
            return u + "?v=20140123"
        }, suggestion: function (t, u) {
            return u + "?v=20140924"
        }, tabs5: function (t, u) {
            return u + "?v=20150429"
        }, tabx: function (t, u) {
            return u + "?v=20140117"
        }, dropdown1: function (t, u) {
            return u + "?v=20140117"
        }, dropdown21: function (t, u) {
            return u + "?v=20140227"
        }, advert: function (t, u) {
            return u + "?v=20140523"
        }, advert2: function (t, u) {
            return u + "?v=20141127"
        }, honourCard: function (t, u) {
            return u + "?v=20140926"
        }, share: function (t, u) {
            return u + "?v=20141107"
        }, qHotCity: function (t, u) {
            return u + "?v=20150326"
        }, mapapi: function (t, u) {
            return u + "?v=20150310"
        }, qunarfilters: function (t, u) {
            return u + "?v=20141114"
        }, renderIframe3: function (t, u) {
            return u + "?v=20150310"
        }, share2: function (t, u) {
            return u + "?v=20150212"
        }, ALD_feedback: function (t, u) {
            return u + "?v=20150113"
        }, addtohome: function (t, u) {
            return u + "?v=20150310"
        }, addtohome2: function (t, u) {
            return u + "?v=20150310"
        }, gpsApi: function (t, u) {
            return u + "?v=20150310"
        }, simGps: function (t, u) {
            return u + "?v=20150310"
        }
    };
    $(document).ready(function () {
        i = true
    });
    g.addDisposeHandler({
        obj: g, fn: function () {
            for (var t in s) {
                if (s.hasOwnProperty(t)) {
                    var u = s[t];
                    while (u.length) {
                        u.pop()
                    }
                }
            }
        }
    });
    function a(u, z) {
        var x = typeof u === "string" ? u.split(/\s*,\s*/) : u;
        if (x.length > 1) {
            if (z) {
                a(x.shift(), function () {
                    if (x.length > 0) {
                        a(x, z)
                    }
                })
            } else {
                while (x.length) {
                    a(x.shift())
                }
            }
            return
        }
        u = x[0];
        if (u === "jquery" && window.jQuery) {
            !g.ui.jquery && (g.ui.jquery = window.jQuery);
            z && z();
            return
        }
        var y = u.replace(/\./g, "/");
        var t = u.replace(/^[\s\S]*\./, "");
        var w = g.uiPrefix + y + "/" + t;
        if (y.search("style/") == 0) {
            o(w + ".css", z)
        } else {
            w += ".js";
            if (l.hasOwnProperty(u)) {
                if (typeof l[u] == "function") {
                    w = l[u](u, w)
                } else {
                    if (typeof l[u] == "string") {
                        w = l[u]
                    }
                }
            } else {
                if (l.hasOwnProperty("*")) {
                    w = l["*"](u, w)
                }
            }
            if (z) {
                r(w, z)
            } else {
                k(w)
            }
        }
    }

    a.cache = b;
    function o(u, w) {
        w = w || m;
        if (u in b) {
            w();
            return
        }
        var t = p.createElement("link");
        t.rel = "stylesheet";
        t.type = "text/css";
        t.href = u;
        t.setAttribute("data-for", "A.ui");
        n.appendChild(t);
        b[u] = 1;
        w()
    }

    function k(t) {
        if (i) {
            r(t, m);
            return
        }
        if (t in b) {
            return
        }
        p.write('<script charset="gb2312" type="text/javascript" src="' + t + '"><\/script>');
        b[t] = 1
    }

    function r(w, x) {
        x = x || m;
        if (w in b) {
            x();
            return
        }
        if (w in j) {
            s[w].push(x);
            return
        }
        j[w] = 1;
        var u = s[w] = [x];
        var t = p.createElement("script");
        t.type = "text/javascript";
        t.charset = "gb2312";
        t.onload = t.onreadystatechange = function () {
            if ((!this.readyState || this.readyState === "loaded" || this.readyState === "complete")) {
                while (u.length) {
                    u.shift()()
                }
                delete j[w];
                b[w] = 1;
                t.onload = t.onreadystatechange = null
            }
        };
        t.src = w;
        t.setAttribute("data-for", "A.ui");
        n.insertBefore(t, n.firstChild)
    }

    g.uicss = function (t) {
        o(g.uiPrefix + t)
    };
    g.uijs = function (t, u) {
        r(g.uiPrefix + t, u)
    };
    g.uijsPathMap = function (t) {
        $.extend(l, t)
    };
    g.use = a;
    g.ui = g.ui || {};
    g.addCssText = function (x) {
        var B = "opui-style-tag-id", u = "data-for", t = "A.ui", w = document.getElementById(B);
        if (!w) {
            w = document.createElement("style");
            w.setAttribute("type", "text/css");
            w.setAttribute(u, t);
            w.id = B;
            document.getElementsByTagName("head")[0].appendChild(w)
        }
        try {
            var y = document.createTextNode(x);
            w.appendChild(y)
        } catch (z) {
            if (w.styleSheet) {
                w.styleSheet.cssText += x
            }
        }
    };
    $(window).on("swap_end", function () {
        var u = /MSIE\s?6/.test(window.navigator.userAgent);
        var t = function (w, y, x) {
            $(w).each(function (C, B) {
                var D = $(B), z = new Image(), E = D.attr("src");
                z.onload = function () {
                    var K = y, H = x, J = this.width, F = this.height, I = (J / F) / (K / H);
                    if (I > 1) {
                        if (J > K) {
                            J = K
                        } else {
                            J = "auto"
                        }
                        F = "auto"
                    } else {
                        if (F > H) {
                            F = H
                        } else {
                            F = "auto"
                        }
                        J = "auto"
                    }
                    D.css({height: F, width: J});
                    z.onload = null;
                    z = null
                };
                z.src = E
            })
        };
        if (u) {
            t("img.result-left-img", 98, 121)
        }
        $(".c-feedback").bind("click", function () {
            var w = this;
            g.use("ALD_feedback", function () {
                var D = "right", x, z, C = $(w);
                if (C.parents("#content_left").length) {
                    D = "left";
                    z = C.parents(".result-op"), x = z.attr("srcid")
                } else {
                    if (C.parents("#content_right").length) {
                        z = C.parents("#con-ar")
                    }
                }
                var y = {query: bds.comm.query, srcid: x, target: z, username: bds.comm.username, flag: D};
                var B = g.ui.ALD_feedback(y);
                g.addDisposeHandler({obj: B, fn: B.dispose})
            })
        })
    })
})();
$(window).on("swap_begin", function () {
    A.dispose();
    A.__reset();
    A.__clearDispose()
});
$(window).on("swap_dom_ready", function () {
    bds.ready(A.__runAla);
    bds.doReady()
});
bds.event = new function () {
    var i = {};

    function d(j, l) {
        if (typeof l == "function" || l instanceof Function) {
            var k = a(j);
            i[k.name] = i[k.name] || [];
            i[k.name].push({prod: k.prod, callback: l})
        }
    }

    function g(l, o) {
        var n = a(l), k = i[n.name] || [], j = 0;
        while (j < k.length) {
            var m = k[j];
            if (o === m.callback && f(n.prod, m.prod)) {
                k.splice(j, 1)
            } else {
                j++
            }
        }
    }

    function b(j, m) {
        var q = a(j), k = i[q.name] || [], r = {data: m, eventId: j};
        for (var n = 0, o = k.length; n < o; n++) {
            var l = k[n];
            try {
                if (f(l.prod, q.prod)) {
                    l.callback.call(this, r)
                }
            } catch (p) {
            }
        }
    }

    function f(j, k) {
        return new RegExp("^" + j.replace(/\./g, "\\.").replace(/\*/g, ".+") + "$").test(k)
    }

    function a(j) {
        var k = j.match(/(.+)\.(.+)/);
        if (k && k[2]) {
            return {prod: k[1], name: k[2]}
        } else {
            return {}
        }
    }

    this.on = d;
    this.off = g;
    this.trigger = b;
    this.events = i
};
function escapeHTML(a) {
    return a.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/&/g, "&amp;").replace(/ /g, "&nbsp;").replace(/"/g, "&#34;").replace(/'/g, "&#39;")
}
function initPreload(bc) {
    document.write = document.writeln = function () {
    };
    function J() {
        Cookie.set("ISSW", "1", null, null, new Date(new Date().getTime() + 300 * 1000))
    }

    if (bds && bds.comm && bds.comm.query == "clearissw") {
        Cookie.clear("ISSW")
    }
    (function () {
        var bq = $.Deferred();
        bds.comm.registerUnloadHandler = function (br) {
            bq.done(br)
        };
        bds.comm.resolveUnloadHandler = function () {
            bq.resolve();
            bq = $.Deferred()
        }
    })();
    function a7(bq) {
        if (bq && typeof bq == "string") {
            bq = $.parseJSON(bq)
        }
        if (bq && bq.length) {
            $.each(bq, function (bs, bt) {
                if (bt.indexOf(bf.protocol) === 0) {
                    var br = new Image();
                    br.src = bt
                }
            })
        }
    }

    function a3(bq) {
        return $.trim(bq).replace(/\s+/g, " ")
    }

    function ay(bs) {
        if (typeof bs == "string") {
            var bq, br = 0;
            for (bq = 0; bq < bs.length; bq++) {
                br += bs.charCodeAt(bq)
            }
            return br
        }
        return 0
    }

    function bn(bv) {
        var bq = {};
        var bu, br, bx, bs;
        if (bv.indexOf("?") > -1) {
            bx = bv.split("?");
            bs = bx[1]
        } else {
            bs = bv
        }
        if (bs.indexOf("&") > -1) {
            bu = bs.split("&")
        } else {
            bu = new Array(bs)
        }
        for (var bt = 0; bt < bu.length; bt++) {
            try {
                bu[bt] = bu[bt].indexOf("=") > -1 ? bu[bt] : bu[bt] + "=";
                br = bu[bt].split("=");
                bq[br[0]] = decodeURIComponent(br[1].replace(/\+/g, " "))
            } catch (bw) {
            }
        }
        return bq
    }

    window.b_rec = function (bs) {
        var bq;
        if (bs) {
            bq = navigator.userAgent
        } else {
            try {
                bq = (window.external && window.external.twGetRunPath) ? window.external.twGetRunPath() : ""
            } catch (br) {
                bq = ""
            }
        }
        bq = bq.replace(/:/, "~").replace(/\t/, "`");
        return bq
    };
    window.scr_rec = function () {
        var bq = "";
        try {
            bq += [document.body.clientWidth, document.body.clientHeight, window.screenTop, window.screenLeft, window.screen.height, window.screen.width].join("_")
        } catch (br) {
        }
        return bq
    };
    window.reh_rec = function () {
        var bs = [], bq = [];
        try {
            $("#content_left").children(".result,.result-op").each(function (bt, bu) {
                bs.push($(bu).height())
            })
        } catch (br) {
        }
        try {
            $("#con-ar").children(".result,.result-op").each(function (bt, bu) {
                bq.push($(bu).height())
            })
        } catch (br) {
        }
        return bs.join("_") + "|" + bq.join("_")
    };
    window.onerror = function () {
        if (window.console && console.debug) {
            console.debug(arguments)
        }
        bds.comm.jserror = Array.prototype.slice.call(arguments).join("\t");
        aC(bds.comm.jserror)
    };
    window.hash = function (br, bq) {
        if (!br) {
            return
        }
        if (br && !bq && ac) {
            return ac.p(br)
        }
        if (br && bq && ac) {
            ac.p(br, bq);
            bf.href = ac.buildSearchUrl()
        }
    };
    var ao, aH, ad, aX, n, bp = false;
    var bl;

    function t(bq) {
        function br(by, bw) {
            if (document.all) {
                $("style[data-for='result']").get(0).styleSheet.cssText += by
            } else {
                var bx = document.createElement("style");
                bx.type = "text/css";
                bx.appendChild(document.createTextNode(by));
                bx.setAttribute("data-for", "result");
                document.getElementsByTagName("HEAD")[0].appendChild(bx)
            }
        }

        if (!bp) {
            var bq = $.extend({top: 93, "z-index": 300}, bq);
            var bv = $(window).height();
            if (!ao) {
                ao = $("<div id='_mask'/>")
            }
            ao.css({
                filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=30)",
                opacity: 0.3,
                position: "absolute",
                background: "#fff",
                "z-index": bq["z-index"],
                top: bq.top + "px",
                left: "0"
            });
            bp = true;
            ao.width(z.width());
            ao.height(Math.max(bv, z.height()) - bq.top);
            ao.appendTo(z);
            var bs = $(window).scrollTop();

            function bu() {
                ao.css({filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=95)", opacity: 0.95});
                if (!aH) {
                    br(".slowmsg{z-index:301;background-color:#fff;border:1px solid #f0f0f0;position:fixed;_position:absolute;top:144px;left:212px;height:95px;width:360px;box-shadow:0 0 5px rgba(0,0,0,0.05)}.slowmsg .ball{width:40px;margin:41px auto 0;position:relative;}.slowmsg .b{left:20px;position:absolute;width:10px;height:10px;-moz-border-radius: 50%;-webkit-border-radius: 50%;border-radius: 50%;}");
                    aH = $('<div class="slowmsg"><div class="ball"><div class="b"/><div class="b"/><div class="b"/></div></div>');
                    aH.find(".b").each(function (bz, bB) {
                        var bw = [[0, 40], [20, 20], [40, 0]][bz];
                        var by = ["rgb(55,137,250)", "rgb(99,99,99)", "rgb(235,67,70)"];
                        var bA = 0;
                        $(bB).css({"background-color": by[bz]});
                        (function bx() {
                            if (!ad) {
                                setTimeout(bx, 400);
                                return
                            }
                            $(bB).animate({left: bw[bA % 2]}, {
                                duration: 800,
                                easing: "swing",
                                progress: function (bD, bC) {
                                    if (bC >= 0.5) {
                                        $(bB).css({"background-color": by[(bA + bz) % 3]})
                                    }
                                },
                                complete: function () {
                                    bx()
                                }
                            });
                            bA++
                        })()
                    })
                }
                aH.appendTo(z);
                ns_c({pj_name: "loading_msg"})
            }

            function bt() {
                var bw, bx = new Date().getTime();
                Cookie.set("rsv_jmp_slow", bx);
                Cookie.set("WWW_ST", bx, null, null, new Date(bx + 30000));
                bw = bf.href + (bf.href.indexOf("?") > 0 ? "&" : "?") + "rsv_jmp=slow";
                bf.replace(bw)
            }

            if (!window.__sam_noloading) {
                ad = setTimeout(bu, 3000);
                n = setTimeout(bt, 11000)
            }
            bl = function () {
                if (ad) {
                    clearTimeout(ad);
                    ad = setTimeout(bu, 3000)
                }
                if (n) {
                    clearTimeout(n);
                    n = setTimeout(bt, 11000)
                }
            }
        }
    }

    function aR() {
        if (ao && bp) {
            bp = false;
            ao.remove();
            if (aH) {
                aH.remove()
            }
            if (ad) {
                clearTimeout(ad);
                ad = false
            }
            if (aX) {
                aX.remove()
            }
            if (n) {
                clearTimeout(n);
                n = false
            }
        }
    }

    function p(bq, bt, br) {
        br || (br = 0);
        var bs = bq.length;
        if (br < 0) {
            br = bs + br
        }
        for (; br < bs; br++) {
            if (bq[br] === bt) {
                return br
            }
        }
        return -1
    }

    (function () {
        var bq = $.globalEval;
        $.globalEval = function (br) {
            var bt = new Date().getTime();
            try {
                bq.apply($, arguments)
            } catch (bs) {
            }
            if (new Date().getTime() - bt > 500) {
            }
        }
    })();
    if (bds.comm.isDebug) {
        $('<style data-for="debug">#debug{display:none!important;}</style>').appendTo("head");
        $('<div id="debug" style="display:block;position:absolute;top:30px;right:30px;border:1px solid;padding:5px 10px;z-index:10000"></div>').appendTo("#wrapper");
        $(window).on("swap_end", function (bu, br) {
            if (br) {
                var bq = $("#isDebugInfo");
                if (!bq.size()) {
                    bq = $('<div id="isDebugInfo"></div>').appendTo("#debug")
                }
                bq.html(br.html.find("#__isDebugInfo").html());
                var bt = "<table>";
                for (var bs in br.log) {
                    if (br.log.hasOwnProperty(bs)) {
                        bt += "<tr><td>" + bs + "</td><td>" + encodeURIComponent(br.log[bs]) + "</td></tr>"
                    }
                }
                bt += "</table>";
                $("#debug").html(bt)
            }
        })
    }
    function M(bu, bt, bv) {
        var bs = bt.find("script:not([src])"), br = 0;
        var bq = $.globalEval;
        $.globalEval = function (bw) {
            window.currentScriptElem = bs[br];
            br++;
            try {
                bq.apply($, arguments)
            } catch (bx) {
                if (window.console && console.debug) {
                    console.debug(bw);
                    console.debug(bx)
                }
            }
        };
        if (bv == "insertBefore") {
            bt.insertBefore(bu)
        } else {
            bu.append(bt)
        }
        window.currentScriptElem = undefined;
        $.globalEval = bq
    }

    function bi(bq) {
        try {
            bq()
        } catch (br) {
            if (window.console && console.debug) {
                console.debug(br)
            }
            aC(br.toString())
        }
    }

    var aC = (function () {
        var bq;
        return function (br) {
            if (bds.comm.isDebug) {
                alert(br)
            }
            if (bds && bds.comm && bds.comm.js_error_monitor) {
                bq = new Image();
                bq.src = bds.comm.js_error_monitor + "?" + $.param({
                    url: bf.href,
                    time: bds.comm.serverTime,
                    explore: navigator.userAgent,
                    info: br,
                    info_type: 1
                })
            }
        }
    })();
    window.setSugKey = function (bq) {
        if (kw && bq) {
            if (o && o.setKey) {
                o.setKey(bq)
            } else {
                kw.val(bq)
            }
        }
    };
    if (bds.comm.flagTranslateResult) {
        $("#wrapper_wrapper").delegate(".result", "mouseenter", function () {
            $(".c-fanyi", $(this)).show()
        });
        $("#wrapper_wrapper").delegate(".result", "mouseleave", function () {
            $(".c-fanyi", $(this)).hide()
        });
        $("#wrapper_wrapper").delegate(".result .c-fanyi", "click", function () {
            var bu = $(this).closest(".result"), br = $("h3 a:first", bu), bs = $(".c-abstract:first", bu), bq = $(".c-fanyi-abstract", bu).val(0).html(), bt = $(".c-fanyi-title", bu).val(0).html();
            $(".c-fanyi-abstract", bu).val(0).html(bs.html());
            $(".c-fanyi-title", bu).val(0).html(br.html());
            br.html(bt);
            bs.html(bq)
        })
    }
    var ap = {use_cache_repeatedly: true, index_form: "#form", kw: "#kw", result_form: "#form"};
    if (bc) {
        $.extend(ap, bc)
    }
    var a2 = 15;
    var bm = 60000;
    var aW = window.__confirm_timeout ? window.__confirm_timeout : 10000;
    var d = !bds.comm.supportis ? 10 : 4;
    var l = (function () {
        var bq = [];

        function br(bs) {
            if (typeof bs != "object" || bs == null) {
                return
            }
            if (bs.xhr && bs.xhr.abort) {
                bs.xhr.abort()
            }
            if (bs.base64) {
                bs.base64.destroy()
            }
            if (bs.pdc) {
                bs.pdc.destroy()
            }
            if (bs.backspace_preload_timeout_id) {
                clearTimeout(bs.backspace_preload_timeout_id)
            }
            delete bs.xhr;
            delete bs.html
        }

        return {
            find: function (bs) {
                return $.grep(bq, bs)
            }, getCacheList: function () {
                var bs = $.map(bq, function (bt) {
                    if (bt && (new Date().getTime() - bt.startTime > bm)) {
                        return false
                    } else {
                        return bt.querySign
                    }
                });
                bs = $.grep(bs, function (bt) {
                    return !!bt
                });
                return bs.join("\t")
            }, hasCache: function (bu, bt) {
                if (!bt) {
                    bt = {}
                }
                var bs = bv(bu);
                if (bs && (new Date().getTime() - bs.startTime > bm)) {
                    this.deleteCache(bs);
                    bs = null
                }
                return bs;
                function bv(bw) {
                    var bx, by;
                    by = bw.p("wd");
                    if (!by) {
                        return false
                    }
                    $.grep(bq, function (bz) {
                        if (bt.loaded && !bz.loaded) {
                            return false
                        }
                        if (bz.real_wd ? (bw.equals(bz.env.clone({wd: bz.real_wd}))) : (bw.equals(bz.env))) {
                            bx = bz
                        }
                    });
                    if (bx) {
                        return bx
                    }
                    return null
                }
            }, shouldShow: function (bs) {
                if (bs.force) {
                    return true
                }
                if (!bs.shouldShow && !bs.force && bs.no_predict) {
                    return false
                }
                var bt = a3(kw.val());
                if (!bt || (ax && bs.env.equals(ax.env))) {
                    return false
                }
                if (bs.env.p("wd").indexOf(bt) == 0) {
                    return true
                }
                if (bs.real_wd.indexOf(bt) == 0) {
                    return true
                }
                return false
            }, getCacheBySign: function (bt) {
                var bs = false;
                $.each(bq, function (bv, bu) {
                    if (!bs && bu.loaded && bu.querySign == bt && (!bu.env.p("pn") || bu.env.p("pn") == 0)) {
                        bs = bu
                    }
                });
                return bs
            }, addCache: function (bs) {
                if (p(bq, bs) != -1) {
                    return
                }
                if (bs.env.p("srcid") || bs.env.p("cq")) {
                    return
                }
                bq.unshift(bs);
                while (bq.length > a2) {
                    br(bq.pop())
                }
            }, deleteCache: function (bs) {
                br(bs);
                bq = $.grep(bq, function (bt) {
                    return bt !== bs
                })
            }, deleteCacheByEnv: function (bs) {
                bq = $.grep(bq, function (bu) {
                    var bt = bu.env.equals(bu.env);
                    if (bt) {
                        br(bu)
                    }
                    return !bt
                })
            }, clearCache: function () {
                bq = $.grep(bq, function (bt, bs) {
                    if (bs !== ax) {
                        br(bs);
                        return false
                    } else {
                        return true
                    }
                });
                bq = []
            }
        }
    })();
    var bf = document.location;
    var at = {
        onurlchange: function () {
        }
    };
    (function () {
        var bu = "onhashchange" in window;
        var br = "onpopstate" in window;
        if (window.__disable_popstate) {
            br = false
        }
        var bw = bf.pathname.length > 1 ? bf.pathname : "/s";
        if (navigator.userAgent.match(/MSIE (6|7)/) || document.documentMode < 8) {
            bu = false;
            br = false
        }
        if (ap.disable_popstate) {
            br = false
        }
        if (!bu && !br) {
            J()
        }
        function bt() {
            var bA = bf.href.match(/#+(.*)$/);
            return bA ? bA[1].replace(/\+/g, "%2B") : ""
        }

        var bq = (function () {
            var bB = "", bA;
            return function (bD, bC) {
                if (bC) {
                    bB = bC.buildQueryString();
                    bf.hash = bB
                }
                if (bD || bB != bt()) {
                    bv(bD);
                    bB = bt()
                }
            }
        })();
        at.setUrl = function (bA) {
            if (br) {
                bs(false, bA)
            } else {
                if (bu) {
                    bq(false, bA)
                }
            }
        };
        function bx() {
            var bA = bf.href.match(/\?([^#]+)/);
            return bA ? bA[1].replace(/\?/g, "&") : ""
        }

        function bz(bC) {
            var bB = "";
            if (window._thirdLinkSpeed === "1") {
                var bA = new Date().getTime();
                bB = "&qid=" + bds.comm.queryId + "&click_t=" + bA
            }
            if (window._eclipse === "1") {
                return "wd=&eqid=" + bds.comm.eqid + by(["pn", "rn", "ie"]) + bB
            }
            if (/wd=/.test(bC)) {
                return bC.replace(/&rsv[^=]*=[^&]*/g, "").replace(/[^a-zA-Z0-9]url=/g, "") + bB
            }
        }

        function by(bC) {
            var bD = "";
            for (var bB in bC) {
                var bA = new RegExp("[?&].*(" + bC[bB] + "=[^&=]+)&?.*");
                matches = bf.search.match(bA);
                if (matches) {
                    bD += "&" + matches[1]
                }
            }
            return bD
        }

        function bv(bA) {
            var bB = new V(bn(at.getQueryString()), true);
            if (!bB.hashCode()) {
                if (pageState != 0) {
                    bf.replace(bf.pathname + bf.search.replace(/([?&])isidx=[^&*]&?/, "$1"))
                } else {
                    if (bf.search != bf.search.replace(/([?&])isidx=[^&*]&?/, "$1")) {
                        bf.replace(bf.pathname + bf.search.replace(/([?&])isidx=[^&*]&?/, "$1"))
                    }
                }
            } else {
                if (pageState == 0) {
                    aw(bB)
                }
            }
            at.onurlchange(bB, bA)
        }

        var bs = (function () {
            var bA = bx(), bB;
            return function (bD, bC) {
                if (bC) {
                    bA = bC.buildQueryString();
                    window.history.pushState(bC, "", bC.buildSyncSearchUrl())
                }
                if (bD || bA != bx()) {
                    bv(bD);
                    bA = bx()
                } else {
                    aR()
                }
            }
        })();
        at.getQueryString = function () {
            if (br) {
                return bx()
            } else {
                if (/wd=/.test(bt())) {
                    return bt()
                } else {
                    return bx()
                }
            }
        };
        at.init = function () {
            if (br) {
                (function () {
                    var bB = bf.href;
                    var bC = false;
                    $(window).on("swap_begin", function () {
                        bC = true
                    });
                    $(window).bind("popstate", function () {
                        if (bC || !bB || bB != bf.href) {
                            bs()
                        }
                        bB = null
                    });
                    $(window).bind("hashchange", function () {
                        var bD = bt();
                        if (/wd=/.test(bD)) {
                            bf.replace(bw + "?" + bD)
                        }
                    })
                })()
            } else {
                if (bu) {
                    $(window).bind("hashchange", function () {
                        bq()
                    });
                    $(function () {
                        bq()
                    })
                }
            }
            var bA = bt();
            if (/wd=/.test(bA)) {
                if (br) {
                    window.history.replaceState(null, "", bw + "?" + bA);
                    bs()
                } else {
                    if (bu) {
                        bq()
                    } else {
                        bf.replace(bw + "?" + bA)
                    }
                }
            }
        };
        at.support = function () {
            return (br || bu) && Cookie.get("ISSW") != 1 && !window.__disable_preload
        };
        at.getLinkParams = function () {
            if (!br) {
                var bA = bt();
                return bz(bA)
            }
            if (bf.protocol === "https:" || window._eclipse === "1") {
                var bB = bx();
                if (!bB) {
                    bB = bt()
                }
                return bz(bB)
            }
            return false
        };
        at.clickResultLink = function (bA, bC, bB) {
            if (br) {
                window.history.pushState(null, "", new V(bB, true).buildSyncSearchUrl());
                bs();
                return false
            } else {
                bA.attr("href", bC.buildSearchUrl(bB)).attr("target", "_self")
            }
        };
        at.submit = function (bB, bA) {
            setTimeout(function () {
                if (br) {
                    window.history.pushState(bB, "", bB.buildSyncSearchUrl());
                    bs(bA)
                } else {
                    if (bu) {
                        bf.href = bB.buildSearchUrl();
                        bq(bA)
                    } else {
                        bf.href = bB.buildSyncSearchUrl()
                    }
                }
            }, 0)
        };
        window.changeUrl = function (bB) {
            var bA = new V(bn(bB));
            at.submit(bA, true)
        }
    })();
    at.onurlchange = function (bs, br) {
        X.done(function () {
            aZ.setKey(bs.p("wd"));
            aZ.hide()
        });
        aV = new Date().getTime();
        kw.val(bs.p("wd"));
        a0("");
        var bq = true;
        if (br && ax && ax.env && ax.env.equals(bs)) {
            bq = false
        }
        u({env: bs, force: true, use_cache: bq, no_predict: true})
    };
    var av = ap.disable ? ap.disable : false;
    if (window.__disable_preload) {
        av = true
    }
    var S = av;
    var E = false;
    if (window.__disable_predict) {
        E = true
    }
    var be = E;
    var k = 200;
    var a5 = 250;
    var aL = 2000;
    var aS = 100;
    var aE = 800;
    var aQ = bds.comm.switchAddMask ? bds.comm.switchAddMask : false;
    if (!aQ) {
        aQ = window.__switch_add_mask ? window.__switch_add_mask : false
    }
    aQ = true;
    var i = bds.comm.preloadMouseMoveDistance ? bds.comm.preloadMouseMoveDistance : 5;
    var b = 300, a = 50, y = 80;
    var D = 0;
    var aI = function () {
    };
    var ar = bn(bf.search);
    if (!at.support()) {
        !function () {
            function bq() {
                bf.hash && bf.hash.match(/[^a-zA-Z0-9](wd|word)=/) && bf.replace("//www.baidu.com/s?" + bf.href.match(/#(.*)$/)[1])
            }

            bf.hash.match(/[^a-zA-Z0-9](wd|word)=/) ? (bf.replace("//www.baidu.com/s?" + bf.href.match(/#(.*)$/)[1]), (function () {
                throw new Error("redirect to sync")
            })()) : (document.getElementById("wrapper").style.display = "block", "onhashchange" in window ? window.onhashchange = bq : setInterval(bq, 200))
        }();
        S = av = true
    }
    var L = Cookie.get("BAIDUID", "nobdid").split(":")[0];
    var aD = L.substr(0, 6) + L.substr(L.length - 5, 5) + parseInt(Math.random() * 99999);
    while (aD.length < 16) {
        aD += "0"
    }
    aD = encodeURIComponent(aD);
    var au, bj;
    var bg = index_kw = kw = $(ap.kw), ab, ba;
    var aV;
    var Z, am, U, aY, aa, aJ;
    var aj = $("#wrapper_wrapper");
    var a9 = [];
    var P = window.__async_strategy;
    au = $(ap.index_form);
    if (au.attr("target") == "_blank") {
        window.__disable_index_predict = true;
        S = av = true
    }
    var bd = au.serializeArray();
    bj = $(ap.result_form);
    var a1 = new Date().getTime();

    function V(bu, bx) {
        if (!V.__init) {
            V.__init = true;
            var bw = ["wd", "pn", "nojc", "cl", "cq", "srcid", "gpc", "tfflag", "si", "lang"];
            var bs = ["wd", "cl", "ct", "tn", "rn", "ie", "f", "lm", "si", "gpc", "tfflag", "usm", "z", "ch", "sts", "vit", "dsp", "trh", "trb", "tre", "la", "lo", "st", "nojc", "haobd", "rtt", "bsst", "gvideo", "__eis", "__eist", "oq", "fenlei", "sid", "rsv_idx", "rsv_stat", "rsv_bp"];
            var bt = ["w", "word"];
            V.prototype.clone = function (bz) {
                var bA = new V(bv(this.params));
                if (typeof bz == "object") {
                    for (var by in bz) {
                        if (bz.hasOwnProperty(by) && p(bs, by) >= 0) {
                            bA.p(by, bz[by])
                        }
                    }
                }
                return bA
            };
            V.prototype.h = function (bz) {
                this.header_params = this.header_params || {};
                for (var by in bz) {
                    this.header_params[by] = bz[by]
                }
                return this
            };
            V.prototype.buildHeaders = function (bA) {
                if (bA) {
                    this.setHeader(bA)
                }
                var bD = {};
                for (var bz in this.header_params) {
                    if (typeof this.header_params[bz] == "object") {
                        var bC = [];
                        for (var bB in this.header_params[bz]) {
                            var by = this.header_params[bz][bB];
                            if (by instanceof Array) {
                                by = by.join("|")
                            }
                            bC.push(bB + "=" + by)
                        }
                        bD[bz] = bC.join("&")
                    } else {
                        bD[bz] = this.header_params[bz]
                    }
                }
                return bD
            };
            V.prototype.buildSearchUrl = function (by) {
                return bf.protocol + "//" + bf.host + bf.pathname + bf.search + "#" + this.buildQueryString(by)
            };
            V.prototype.buildSyncSearchUrl = function (by) {
                return bf.protocol + "//" + bf.host + "/s?" + this.buildQueryString(by)
            };
            V.prototype.buildQueryString = function (bA) {
                var bz = bv(this.params);
                if (typeof bA == "object") {
                    for (var by in bA) {
                        if (bA.hasOwnProperty(by)) {
                            bz[by] = bA[by]
                        }
                    }
                }
                var bB = "";
                bz.wd = $.limitWd(bz.wd);
                for (param in bz) {
                    if (param && bz.hasOwnProperty(param) && bz[param] !== "") {
                        bB += param + "=" + encodeURIComponent(bz[param]).replace(/'/g, "%27") + "&"
                    }
                }
                return bB.substr(0, bB.length - 1)
            };
            V.prototype.equals = function (bz) {
                if (!bz || !bz.p) {
                    return false
                }
                for (var bA = 0; bA < bw.length; bA++) {
                    var by = bw[bA];
                    if (by == "pn") {
                        var bC = this.p(by) ? this.p(by) : "0";
                        var bB = bz.p(by) ? bz.p(by) : "0";
                        if (bC != bB) {
                            return false
                        }
                    } else {
                        if (this.p(by) != bz.p(by)) {
                            return false
                        }
                    }
                }
                return true
            };
            V.prototype.p = function (bz, by) {
                if (p(bt, bz) >= 0) {
                    bz = "wd"
                }
                if (by === undefined) {
                    return this.params[bz]
                }
                this.params[bz] = by;
                return this
            };
            V.prototype.hashCode = function () {
                var bz = [];
                if (!this.p("wd")) {
                    return ""
                }
                for (var bA = 0; bA < bw.length; bA++) {
                    var by = bw[bA];
                    if (by == "pn" && !this.p(by)) {
                        bz.push("0")
                    } else {
                        bz.push(this.p(by))
                    }
                }
                return bz.join("\t")
            };
            V.prototype.filterOtherParams = function () {
                for (var by in this.params) {
                    if (this.params.hasOwnProperty(by) && p(bs, by) < 0) {
                        delete this.params[by]
                    }
                }
            };
            V.prototype.wdSameName = function () {
                var by;
                for (by = 0; by < bt.length; by++) {
                    if (this.params && this.params[bt[by]]) {
                        this.params.wd = this.params[bt[by]];
                        delete this.params[bt[by]]
                    }
                }
            }
        }
        this.params = {};
        if (!bx) {
            bd = au.serializeArray();
            for (var br = 0; br < bd.length; br++) {
                if (!this.p(bd[br].name)) {
                    this.p(bd[br].name, bd[br].value)
                }
            }
        }
        if (typeof bu == "object") {
            for (var bq in bu) {
                if (bu.hasOwnProperty(bq)) {
                    this.p(bq, bu[bq])
                }
            }
        }
        this.wdSameName();
        function bv(bz) {
            if (typeof bz == "object") {
                var by = {};
                for (bq in bz) {
                    if (bz.hasOwnProperty(bq)) {
                        by[bq] = bz[bq]
                    }
                }
            } else {
                by = bz
            }
            return by
        }
    }

    window.pageState = 0;
    var ac = null;
    var ax = null;
    var a8 = document.location.href;
    var aq = false;
    var aZ, aM, o;
    var X = $.ajax({
        dataType: "script",
        cache: true,
        url: bds.comm.logFlagSug === 1 ? "https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/sug/js/bdsug_async_sam_sug_a1a6b55b.js" : window._is_pc_direct_sam ? "https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/sug/js/bdsug_input_event_pc_direct_bef72dc3.js" : "https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/sug/js/bdsug_async_7bc1840c.js"
    });
    var an;
    var r = "focus";
    var O;
    (function () {
        window.PDC_ASYNC = {
            setParam: function (bs, bt) {
                if (ax && ax.pdc) {
                    ax.pdc.setParam(bs, bt)
                }
            }, setLinkData: function (bs, bt) {
                bq[bs] = bt
            }, sendLinkLog: function () {
                var bv = "//www.baidu.com/nocache/fesplg/s.gif?log_type=linksp", bw = "";
                bw += "&link_t=" + (new Date().getTime() - bq.click_t) + "&query=" + bds.comm.queryEnc + "&qid=" + bds.comm.queryId;
                var bu = Math.random();
                if (bu < 0.01) {
                    var bs = new Image(), bt = "LINK_IMG_" + (new Date());
                    window[bt] = bs;
                    bs.onload = function () {
                        delete window[bt]
                    };
                    bs.src = bv + bw
                }
            }
        };
        var br = window.PDC_ASYNC.log = {};
        var bq = {};
        window.bds && (bds.pdc = window.PDC_ASYNC)
    })();
    function aF(bM) {
        var bF = {product_id: 45, page_id: 317, page_type: 0}, bu = {}, bQ = {
            st: 0,
            pt: 0,
            net: 0,
            dom: 0,
            fs: 0
        }, bJ = [], br = $.Callbacks(), bs = {}, by = null, bv = null, bw = 600;

        function bB(bR) {
            if (typeof bR === "string") {
                bQ[bR] = Date.now ? Date.now() : +new Date()
            }
        }

        function bK(bR, bS) {
            if (typeof bR === "string") {
                bu[bR] = bS
            }
        }

        function bD() {
            bu.cus_net = bQ.net > bQ.st && bQ.net - bQ.st - bu.cus_srv > 0 ? bQ.net - bQ.st - bu.cus_srv : 1;
            bu.cus_tti2 = bQ.dom > bQ.st ? bQ.dom - bQ.st : 1;
            bu.cus_frdom = bQ.dom - bQ.pt;
            bu.cus_fs = bQ.fs > bQ.st ? bQ.fs - bQ.st : bu.cus_tti2;
            bu.cus_frext = bu.cus_fs - bu.cus_tti2
        }

        function bC(bS) {
            var bT = "";
            for (var bR in bS) {
                if (bR && bS.hasOwnProperty(bR) && bS[bR] !== "") {
                    bT += "&" + bR + "=" + encodeURIComponent(bS[bR])
                }
            }
            return bT
        }

        function bI(bS) {
            var bS = [];
            for (var bT in bs) {
                bS.push(bs[bT])
            }
            var bR = by = $.when.apply($, bS);
            by.always(function () {
                if (bR !== by) {
                    return
                }
                bN()
            })
        }

        function bH() {
            var bS = Array.apply(null, arguments);
            if (!bS.length > 0) {
                return
            }
            for (var bR = 0; bR < bS.length; bR++) {
                bs[bS[bR]] = $.Deferred()
            }
        }

        function bz() {
            bK("qid", bM.qid);
            bK("cus_q", (bM.real_wd || bM.env.p("wd")));
            bK("sid", bds.comm.sid);
            bK("cus_newindex", bds.comm.newindex);
            bK("supportis", bds.comm.supportis)
        }

        function bP() {
            bM = null;
            bv = null
        }

        function bE(bR) {
            bs[bR].resolve();
            if (bR == "swap_end") {
                setTimeout(function () {
                    bE("swap_end_5s")
                }, 5000)
            }
        }

        function bx() {
            bH("swap_end", "swap_end_5s");
            if (bds.comm.supportis || !window._async_merge) {
                bH("confirm")
            }
            bI()
        }

        function bL() {
            bx();
            bQ.st = 0;
            bQ.fs = 0;
            bQ.dom = 0
        }

        function bN() {
            var bV = Math.random(), bX = /13549|13551|13686|13687|13701|13702|13156|13157|13854|13855|13468|13469|13470|13471|13514|13515|14524|14525|14614|14615/, bR = bV > 0.51 && bV < 0.52;
            if (((bV > 0.51 && bV < 0.52) || (bX.test(bds.comm.sid) && (bV > 0 && bV < 0.2)) || bds.comm.intrSid)) {
                if (bX.test(bds.comm.sid)) {
                    if (!bR) {
                        bK("issam", 1)
                    } else {
                        bK("issam", 2)
                    }
                }
                bD();
                bA(bv);
                bt(bv);
                bK("srvInfo", bG());
                bz();
                br.fire();
                var bW = "//www.baidu.com/nocache/fesplg/s.gif?log_type=sp", bY = "";
                bY += bC(bF) + bC(bu);
                var bT = bW + bY, bS = new Image(), bU = "_LOG_" + new Date().getTime();
                bS.onload = function () {
                    delete window[bU]
                };
                window[bU] = bS;
                bS.src = bT
            }
        }

        function bq(bR) {
            bv = bR;
            bR.find("img").one("load", function () {
                var bW = $(this).offset(), bU = bW.top, bV = bW.left, bT = "";
                if (bU < bw && bU > 0) {
                    bB("fs");
                    var bS = bQ.fs - bQ.dom;
                    bJ.push(bU + "_" + bV + "_" + bS);
                    if ($(this).attr("data-src") || /^http/.test($(this).attr("src"))) {
                        bT = $(this).attr("data-src") || $(this).attr("src")
                    } else {
                        bT = "base64"
                    }
                    bK("ic_lis", bT)
                }
            })
        }

        function bG() {
            var bU = $.parseJSON(bds.comm.speedInfo), bR = "", bT = [], bX, bW;
            for (var bV in bU) {
                bX = bU[bV];
                bW = bX.ModuleId + "_" + bX.TimeCost + "_" + bX.TimeSelf + "_" + bX.Idc;
                if (bX.hasOwnProperty("SubProcess")) {
                    for (var bS in bX.SubProcess) {
                        bW += "," + bX.SubProcess[bS].ProcessId + "_" + bX.SubProcess[bS].TimeCost + "_" + bX.SubProcess[bS].isHitCache + "_" + bX.SubProcess[bS].Idc
                    }
                }
                bT.push(bW)
            }
            return encodeURIComponent(bT.join("|"))
        }

        function bA(bS) {
            var bX = 0, bW = bS.find("img"), bV = bS.find("#content_left").find("img"), bY = 0, bR = 0, bU = 0;
            for (var bT = 0; bT < bW.length; bT++) {
                bU = bW.eq(bT).offset().top;
                if (bU < bw && bU > 0) {
                    bX++
                }
            }
            bK("cus_ic", bW.length);
            bK("cus_extic", bX);
            bK("cus_extlic", bY);
            bK("cus_icl", bV.length);
            bK("cus_icr", bS.find("#content_right").find("img").length);
            bK("img_info", bJ.join(","));
            bK("psize", bS.html().length)
        }

        function bt(bR) {
            var bW = {}, bV = [], bU = bR.find("#content_left,#con-ar").children("*[tpl]"), bS = "";
            if (bU.length > 0) {
                for (var bT = 0; bT < bU.length; bT++) {
                    bS = bU.eq(bT).attr("tpl");
                    if (!bW.hasOwnProperty(bS)) {
                        bW[bS] = 1;
                        bV.push(bS)
                    }
                }
            }
            if (bV.length > 0) {
                bK("tplp", bV.join("|"))
            }
        }

        function bO(bR) {
            br.add(bR)
        }

        bx();
        return {trigger: bE, mark: bB, setParam: bK, onSendlog: bO, bindImgLoad: bq, destroy: bP, init: bL}
    }

    var aK = (function (bu) {
        function bs() {
            if (bw != 1, (bw = 1, by()), bw == 1) {
                var bB = new Date(), bC = false, bD = function () {
                    var bG = new Date(), bE = bG - bB - bu, bF = bt();
                    0 > bE && (bE = 0);
                    if (!bF && !bC) {
                        bq[bz] = bE;
                        bz = (bz + 1) % 20
                    }
                    bC = bF;
                    1 == bw && (bB = bG, br = setTimeout(bD, bu))
                };
                br = setTimeout(bD, bu)
            }
        }

        function by() {
            window.clearTimeout(br)
        }

        function bt() {
            var bC = ["webkit", "moz", "ms", "o"];
            if ("hidden" in document) {
                return document.hidden
            }
            for (var bB = 0; bB < bC.length; bB++) {
                if (bC[bB] + "Hidden" in document) {
                    return document[bC[bB] + "Hidden"]
                }
            }
            return false
        }

        function bx() {
            bq.slice(bz).concat(bq.slice(0, bz))
        }

        function bA(bL) {
            try {
                var bH = 0, bK = Math.max.apply(null, bL);
                var bM = 10, bD = 60, bC = 4, bJ = 40;
                var bF = Cookie.get("ispeed_lsm"), bB = 0, bO = new Date(), bG = 0;
                bO.setTime(bO.getTime() + 30 * 86400000);
                for (var bE = 0; bE < bL.length; bE++) {
                    var bN = bL[bE];
                    bH += bN
                }
                bH = Math.round(bH / bL.length);
                if (bK > 1000 || bH > 150) {
                    bB = bF ? parseInt(bF) : 0;
                    if (bB >= bJ - bM && bB < bJ) {
                        bG = 1;
                        Cookie.set("ispeed", 2, document.domain, "/", bO)
                    }
                    if (bB < bD) {
                        bB = bB + bM > bD ? bD : bB + bM;
                        Cookie.set("ispeed_lsm", bB, document.domain, "/", bO)
                    }
                } else {
                    if (bF && parseInt(bF) > bJ && /13455/.test(bds.comm.sid)) {
                        bC = 5
                    }
                    if (bF && parseInt(bF) >= bC) {
                        if (parseInt(bF) <= bJ + bC && parseInt(bF) > bJ) {
                            bG = 2;
                            Cookie.set("ispeed", 1, document.domain, "/", bO)
                        }
                        bB = parseInt(bF) - bC;
                        Cookie.set("ispeed_lsm", bB, document.domain, "/", bO)
                    }
                }
                return bG
            } catch (bI) {
            }
            return 0
        }

        function bv() {
            if (Cookie.get("ispeed") && UPS.get("isSwitch") == 1 && window.ispeed == true && (/13455|13457/.test(bds.comm.sid))) {
                return parseInt(Cookie.get("ispeed")) == 2 ? true : false
            }
            return false
        }

        var bq = [], bz = 0, bw = 0, br = false, bu = bu || 250;
        return {
            start: bs, stop: function () {
                window.clearTimeout(br);
                bw = 0
            }, getData: function () {
                return bq.slice(bz).concat(bq.slice(0, bz))
            }, isSlow: bv, monitor: bA
        }
    })();
    if (bds.comm.supportis && UPS.get("isSwitch") == 1) {
        aK.start()
    }
    var T;
    X.done(function () {
        aZ = aM = o = bds.se.sug({
            maxNum: 10,
            withoutRich: bds.comm.supportis,
            withoutZhixin: true,
            form: bj[0],
            ipt: kw[0],
            cbname: "bdsugresultcb",
            submission: aG
        });
        kw.keydown(function (bs) {
            var bt = m(this);
            if ((bs.keyCode == 9 || (bs.keyCode == 39 && bt == this.value.length)) && bds.comm.supportis && ax && ab.text()) {
                bs.preventDefault();
                if (ax.real_wd != this.value) {
                    kw.val(ax.real_wd);
                    aZ.check()
                }
                aZ.show();
                a0("");
                w(ax, aq, 22)({tipConfirm: true})
            }
        });
        aZ.on("start", function () {
            r = "focus"
        });
        $(window).on("blur", function () {
            aZ.hide()
        });
        $(document).on("click", function (bs) {
            if (bs.isTrigger == 2 || bs.isTrigger == 3) {
                return false
            }
            aZ.hide()
        });
        var bq, br;
        aZ.on("inputChange", function (bx, bw) {
            if (!bq) {
                bq = kw.val()
            }
            a0("");
            a6();
            clearTimeout(T);
            if ((pageState == 0 && window.__disable_index_predict) || av || E || aK.isSlow()) {
                aZ.setMaxNum(10);
                return
            }
            aZ.setMaxNum(d);
            var by = new V({pn: "", wd: bw.value});
            if (bds && bds.comm && bds.comm.logFlagSug && bds.comm.logFlagSug === 1) {
                by.p("rsv_slog", "ipt_change")
            }
            if (pageState == 0 && bds.comm.supportis && $.trim(kw.val())) {
                aw(by);
                var bv = $("<div id='ent_sug'>请按“回车”键发起检索</div>").insertBefore("#head");
                $(window).one("swap_begin", function () {
                    bv.remove()
                })
            }
            if (O) {
                O = false;
                return
            }
            if (window.__restart_confirm_timeout) {
                al()
            }
            r = "input";
            aV = new Date().getTime();
            if (br) {
                clearTimeout(br);
                br = false
            }
            if ($.trim(bw.value) == "") {
                f();
                return
            }
            an = bw.checkStore();
            if (!/^[a-zA-Z0-9~!@#$%^&*()_+=-]$/.test(bw.value)) {
                var bt = kw.val();
                var bs = bw.imt.getLog();
                if (bs.length > 0) {
                    if (bs[bs.length - 1].type.indexOf("link") > -1) {
                        by.p("_sglink", "1")
                    }
                }
                var bu = bw.imt.diffLog();
                if (bu && bu.length > 1) {
                    bu = bu.slice(-3 * 5).join(".");
                    by.h({is_params: {imes: encodeURIComponent(bu)}})
                }
                if (!window.__disable_is2 && bq.length > bt.length && bq.indexOf(bt) === 0) {
                    br = setTimeout(function () {
                        u({env: by, use_cache: true, force: false, pstg: 5, shouldShow: bds.comm.supportis});
                        br = false
                    }, 250)
                } else {
                    u({env: by, use_cache: true, force: false, pstg: 5, shouldShow: bds.comm.supportis})
                }
            }
            bq = bt
        });
        aZ.on("selectSug", function (bw, bv, bu, bt) {
            a0("");
            clearTimeout(T);
            if ((pageState == 0 && window.__disable_index_predict) || av || E || aK.isSlow()) {
                return
            }
            if (bu == -1) {
                if (ax) {
                    aZ.setVisibleSug(ax.real_wd)
                }
                var bx = new V({pn: "", wd: bv.value});
                if (bds && bds.comm && bds.comm.logFlagSug && bds.comm.logFlagSug === 1) {
                    bx.p("rsv_slog", "sug_select")
                }
                u({env: bx, use_cache: true, force: false, shouldShow: bds.comm.supportis, pstg: 3})
            } else {
                aZ.setVisibleSug();
                a0("");
                var bx = new V({pn: "", wd: bt});
                if (bds && bds.comm && bds.comm.logFlagSug && bds.comm.logFlagSug === 1) {
                    bx.p("rsv_slog", "sug_select")
                }
                var bs = (bv.stopRefresh) ? false : (bds.comm.supportis);
                u({env: bx, force: false, use_cache: true, no_predict: true, shouldShow: bs, pstg: 3})
            }
        });
        aZ.on("render", function (bt, bs) {
            if (ax) {
                aZ.setVisibleSug(ax.real_wd)
            }
        });
        aZ.on("dataReady", function (bv, bu) {
            var bt = bu && bu.queryValue && bu.dataCached && bu.dataCached[bu.queryValue];
            if (bt && bt.gl) {
                for (var bs in bt.gl) {
                    if (bt.gl[bs] * 1 > 0) {
                        var bw = new V({pn: "", wd: bt.s[bs]});
                        u({env: bw, force: false, use_cache: true, no_predict: true, shouldShow: false, pstg: 7})
                    }
                }
            }
        });
        if (pageState == 0) {
            aZ.start()
        }
    });
    function m(bs) {
        var br = 0;
        if (document.selection) {
            var bq = document.selection.createRange();
            bq.moveStart("character", -bs.value.length);
            br = bq.text.length
        } else {
            if (bs.selectionStart || bs.selectionStart == "0") {
                br = bs.selectionStart
            }
        }
        return (br)
    }

    function aO(bq, br) {
        if (bq) {
            br = $.extend(bq.log, br)
        }
    }

    function aT() {
        if (bds.comm.seinfo) {
            bds.comm.seinfo.rsv_pre = encodeURIComponent(C());
            bds.comm.seinfo.rsv_reh = reh_rec();
            bds.comm.seinfo.rsv_scr = scr_rec();
            var br = null;
            if (bds && bds.comm && bds.comm.personalData) {
                try {
                    if (typeof bds.comm.personalData == "string") {
                        bds.comm.personalData = $.parseJSON(bds.comm.personalData)
                    }
                    if (!bds.comm.personalData) {
                        br = null
                    } else {
                        br = bds.comm.personalData.fullSkinName && bds.comm.personalData.fullSkinName.value
                    }
                } catch (bs) {
                    br = null
                }
            }
            if (br) {
                bds.comm.seinfo.rsv_skin = br
            }
            bds.comm.seinfo.rsv_psid = $.getCookie("BIDUPSID");
            bds.comm.seinfo.rsv_pstm = $.getCookie("PSTM");
            c(bds.comm.seinfo);
            if (bds.comm._se_click_track_flag === "ON") {
                var bt = new Image(), bu = "//www.baidu.com/s?wd=" + bds.comm.queryEnc + "&qid=" + bds.comm.queryId + "&lts=91";
                bt.src = bu
            }
        }
        if (bds.comm.cgif) {
            var bq = bds.comm.cgifimg = new Image();
            bq.src = bds.comm.cgif
        }
        (function () {
            var bA = Math.random(), by = [], bz = function (bC, bE) {
                var bB = $(bC), bG = "", bF;
                if (bC == "link" && bB.attr("rel") != "stylesheet") {
                    return
                }
                for (var bD = 0; bD < bB.length; bD++) {
                    bF = bB.eq(bD);
                    bG = bF.attr(bE);
                    if (bx(bG)) {
                        by.push(encodeURIComponent(bG))
                    }
                }
            }, bx = function (bB) {
                if (/^http:\/\//.test(bB)) {
                    return true
                }
                return false
            }, bw = function () {
                var bD = "//www.baidu.com/nocache/fesplg/s.gif?log_type=hm", bE = "";
                bE += "&q=" + bds.comm.query;
                bE += "&error=" + by.join(",");
                var bB = new Image(), bC = "_HM_LOG_" + new Date().getTime();
                bB.onload = function () {
                    delete window[bC]
                };
                window[bC] = bB;
                bB.src = bD + bE
            }, bv = function () {
                var bD = Math.floor(Math.random() * 4);
                var bB = {
                    www: "https://www.baidu.com/nocache/pdns/az.gif?t=" + (+new Date()),
                    cdn: "https://ss" + bD + ".baidu.com/6ONWsjip0QIZ8tyhnq/ps_default.gif?t=" + (+new Date()),
                    idc: "https://sp" + bD + ".baidu.com/htpoty.gif?t=" + (+new Date())
                };
                var bC = [], bF = [], bH = {};
                for (var bE in bB) {
                    (function (bL) {
                        var bK = "_SSL_LOG_" + bL + "_" + (+new Date()), bI = new Image(), bJ = new Date();
                        bH[bL] = $.Deferred();
                        bF.push(bH[bL]);
                        bI.onload = function () {
                            bH[bL].resolve();
                            delete window[bK]
                        };
                        bI.onerror = function () {
                            bH[bL].resolve();
                            bC.push(bL + "_" + bD + "=" + (new Date() - bJ));
                            delete window[bK]
                        };
                        bI.src = bB[bL]
                    })(bE)
                }
                var bG = $.when.apply($, bF);
                bG.always(function () {
                    var bK = "//www.baidu.com/nocache/fesplg/s.gif?log_type=hm&type=ssl&", bL = bC.join("&");
                    var bI = new Image(), bJ = "_HM_LOG_" + new Date().getTime();
                    bI.onload = function () {
                        delete window[bJ]
                    };
                    window[bJ] = bI;
                    bI.src = bK + bL
                })
            };
            if (bf.protocol === "https:" && bA < 0.03) {
                bz("img", "src");
                bz("script", "src");
                bz("iframe", "src");
                bz("link", "href");
                if (by.length > 0) {
                    bw()
                }
            }
            if (bf.protocol === "http:" && bA < 0.01) {
                bv()
            }
            if (window.ctwin) {
                window.ctwin.sendRequest(bf.href, {content_syns: 1})
            }
        })()
    }

    function C() {
        return a9.length
    }

    var ag, F, bh, x;
    (function () {
        var bt;
        var bx = -1, bw = 0;
        var br = -1, bu = -1, bq = -1, bs = -1;
        var bv = 0;
        ag = function (bz) {
            if (!bz) {
                return
            }
            bq = bz.pageX;
            bs = bz.pageY;
            bt = bz.target;
            var by = $(bz.target);
            if (by.attr("type") == "submit") {
                bv = 1
            }
            var bA = by.offset();
            br = bq - bA.left;
            bu = bs - bA.top;
            bw = new Date().getTime()
        };
        F = function (by) {
            if (!by || by.target != bt) {
                return
            }
            bx = new Date().getTime() - bw
        };
        x = function (bz) {
            if (bds && bds.comm && bds.comm.query) {
                bz = bds.comm.query
            }
            var by = bv + "." + bx + "." + br + "." + bu + "." + bq + "." + bs;
            by = ay(by + bz) + "." + by;
            return by
        };
        bh = function () {
            bx = -1;
            bw = 0;
            br = -1;
            bu = -1;
            bq = -1;
            bs = -1;
            bv = 0
        };
        $(window).on("confirm", function () {
            setTimeout(bh, 0)
        })
    })();
    $(function () {
        $("#head").delegate(".index_tab_top>a,.index_tab_bottom>a,#u1>a", "mousedown", function () {
            return ns_c({fm: "behs", tab: $(this).attr("name"), query: "", un: encodeURIComponent(bds.comm.user || "")})
        })
    });
    $(document).delegate("a", "mousedown", function () {
        w(ax, aq, 22)()
    });
    function I(bq) {
        $(document).delegate("a", "mousedown", (function () {
            return function () {
                var bw = bq.prefix;
                var bv = $(this);
                var bs = bv.attr("href");
                var br;
                if (bw && bs && bs.indexOf(bw) == 0) {
                    bs = bs.substring(bw.length)
                }
                if (!bw && bs) {
                    var bt = bs.match(/^http:\/\/[^\/]+/);
                    if (bt) {
                        bw = bt[0]
                    } else {
                        return
                    }
                    bs = bs.replace(/^http:\/\/[^\/]+/, "")
                }
                if (bs) {
                    br = bs.match(bq.regex)
                }
                if (br && br[2] && br[2].match(/&(wd|word)=/)) {
                    return
                }
                if (bs && br) {
                    if (bq.convertTable && bq.convertTable[br[1]]) {
                        br[1] = bq.convertTable[br[1]]
                    }
                    var bu = at.getLinkParams();
                    if (bu) {
                        if (bf.protocol === "https:" && /Chrome/.test(navigator.userAgent)) {
                            bw = bw.replace(/^http:\/\/www\.baidu\.com/, "https://www.baidu.com")
                        }
                        bv.attr("href", bw + "/" + br[1] + "?" + br[2] + "&" + bu);
                        bv.click(function () {
                            window.PDC_ASYNC.setLinkData("click_t", new Date().getTime())
                        })
                    }
                }
            }
        })())
    }

    I({prefix: "http://www.baidu.com", regex: /^\/*(link)\?(.*)$/});
    I({prefix: "//www.baidu.com", regex: /^\/*(link)\?(.*)$/});
    I({
        prefix: "http://www.baidu.com",
        convertTable: {
            "baidu.php": "baidu.php",
            "aladdin.php": "aladdin.php",
            "siva.php": "siva.php",
            "adrc.php": "adrc.php",
            "zhixin.php": "zhixin.php"
        },
        regex: /^\/*(baidu\.php|aladdin\.php|siva\.php|adrc\.php|zhixin\.php)\?(.*)$/
    });
    if (bf.host != "www.baidu.com") {
        I({
            prefix: "",
            convertTable: {
                "baidu.php": "baidu.php",
                "aladdin.php": "aladdin.php",
                "siva.php": "siva.php",
                "adrc.php": "adrc.php",
                "zhixin.php": "zhixin.php"
            },
            regex: /^\/*(baidu\.php|aladdin\.php|siva\.php|adrc\.php|zhixin\.php)\?(.*)$/
        })
    }
    I({prefix: "http://bzclk.baidu.com", regex: /^\/*(adrc\.php)\?(.*)$/});
    if (bf.protocol == "https:" && bds.comm.ishome && !/Chrome/.test(navigator.userAgent)) {
        $(document).delegate("a", "mousedown", function () {
            var bs = $(this);
            var bq = bs.attr("href");
            var br = {"http://v.baidu.com": "/?fr=bd"};
            if (br && br.hasOwnProperty(bq)) {
                bs.attr("href", bq + br[bq])
            }
        })
    }
    if (at.support()) {
        $(document).delegate("a", "click", (function () {
            var bq = bf.protocol + "//" + bf.host;
            return function (bv) {
                var bu = $(this);
                if (bu.attr("target") && bu.attr("target") != "_self") {
                    return
                }
                var bs = $.trim(bu.attr("href"));
                if (bs && bs.indexOf(bq) == 0) {
                    bs = bs.substring(bq.length)
                }
                if (bs) {
                    matched = bs.match(/^\/*s\?(.*)/)
                }
                if (bs && matched) {
                    var bw = bn(matched[0]);
                    if (!bw.pn) {
                        bw.pn = ""
                    }
                    if (p(["baidurt", "baiduwb", "baidufir", "SE_baiduxueshu_c1gjeupa"], bw.tn) < 0) {
                        var br = bu.parents("#con-at");
                        if (br.size() > 0) {
                            t({top: br.offset().top + br.height()})
                        }
                        var bt = at.clickResultLink(bu, ac, bw)
                    }
                    return bt
                }
            }
        })())
    }
    $(document).delegate("a", "mousedown", function (bq) {
        ag(bq)
    });
    $(document).delegate("a", "mouseup", function (bq) {
        F(bq)
    });
    $(document).delegate("#su,#su1", "mouseup", function (bq) {
        F(bq)
    });
    $(document).delegate("#su,#su1", "mousedown", function (bq) {
        ag(bq)
    });
    var z = $("body");
    var W = document.title;
    (function (br) {
        var bq;
        br.fn.textWidth = function () {
            if (!bq) {
                bq = br('<div data-for="result" style="clear:both;display:block;visibility:hidden;position:absolute;top:0;"><span style="width;inherit;margin:0;font:16px/22px arial;"></span></div>').appendTo("body").find("span")
            }
            bq.html(escapeHTML(br(this).val()));
            var bs = bq.width();
            return bs
        }
    })(jQuery);
    function a0(bq) {
        if (window.__disable_is2 && $.trim(bq) == $.trim(kw.val())) {
            return
        }
        if (B || !bds.comm.supportis) {
            if (ab) {
                ab.html("")
            }
            return
        }
        if (pageState == 0) {
            return
        }
        if (window.__disable_kw_tip) {
            return
        }
        if (!ab) {
            ab = $('<div id="kw_tip" style="width:initial" unselectable="on" onselectstart="return false;" class="s_ipt_tip"/>').insertBefore(kw);
            ab.parent().click(function (bv) {
                var bu = kw.get(0);
                if (bv.target === bu) {
                    return true
                }
                bu.focus();
                var bs = bu.value.length;
                if (document.selection) {
                    var bt = bu.createTextRange();
                    bt.moveStart("character", bs);
                    bt.collapse();
                    bt.select()
                } else {
                    if (typeof bu.selectionStart == "number" && typeof bu.selectionEnd == "number") {
                        bu.selectionStart = bu.selectionEnd = bs
                    }
                }
                return false
            });
            ab.get(0).onselectstart = function () {
                return false
            }
        }
        ab.text(bq);
        if (bq != "") {
            var br = kw.textWidth();
            ab.css({"margin-left": br + 10 + "px", "max-width": ab.parent().width() - br - 14 + "px"}).text(bq);
            if (window.__disable_is2) {
                ab.css("z-index", 1)
            }
            ab.show()
        } else {
            ab.hide()
        }
    }

    var B = false;

    function aA() {
        B = false
    }

    function f(bq) {
        B = true;
        if (window.__disable_swap_to_empty) {
            if (ax && ax.real_wd && $.trim(kw.val())) {
                a0(ax.real_wd);
                N(ax)
            } else {
                a0("");
                N()
            }
            return
        }
        clearTimeout(aa);
        aa = false;
        aj.hide();
        if (pageState == 0) {
            aw(bq)
        }
        aq = ax;
        ax = null;
        ac = null;
        bb();
        if (bds && bds.se && bds.se.certification && bds.se.certification.data) {
            bds.se.certification.data = []
        }
        bi(function () {
            aI()
        });
        bds.clearReady();
        if ($.trim(kw.val())) {
            aj.html("<div style='margin:30px 0 0 162px;font-size:13px;color:#666'>请按“回车”键发起检索</div>")
        } else {
            aj.empty()
        }
        document.title = "百度搜索";
        a0("");
        aj.show()
    }

    function N(bq) {
        var br = a3(kw.val());
        if (bq && br == bq.real_wd) {
            $("#super_se_tip").remove()
        }
    }

    $(window).on("swap_dom_ready", function (bs, bq) {
        var br = "";
        if (bq && bq.real_wd && (!bq.no_predict || bq.pstg == 6)) {
            br = bq.real_wd
        }
        a0(br);
        N(bq)
    });
    $(window).on("swap_end", function (br, bq) {
        if (!bq) {
            return
        }
        if (aa) {
            clearTimeout(aa);
            aa = false;
            aJ = null
        }
        bq.confirm = false;
        if (!bq.force) {
            aJ = w(bq, aq, 21);
            aa = setTimeout(aJ, aW)
        } else {
            w(bq, aq, 20)()
        }
        if (window.__async_merge_mod && ((navigator && navigator.userAgent) ? navigator.userAgent : "").match(/(msie [2-8])/i)) {
            setTimeout(function () {
                l.clearCache()
            }, 100)
        }
    });
    function aU(bv, bu) {
        var bt = new Date().getTime();
        if (!bu.force) {
            aO(bu, {utime: new Date().getTime() - a1})
        }
        if (!bu || !bu.loaded) {
            return false
        }
        if (typeof bu.html == "string") {
            bu.html = $(bu.html)
        }
        $(bu).trigger("swap_begin");
        bi(function () {
            bu.pdc.mark("pt");
            $(window).trigger("swap_begin", [bu, bv]);
            var bz = aK && aK.getData();
            if (bz) {
                setTimeout(function () {
                    bu.pdc.setParam("ispeed", aK.monitor(bz))
                }, 3000);
                bu.pdc.setParam("upm", bz.join(","))
            }
        });
        bi(function () {
            bu.base64.restart();
            try {
                if (!bu.base64_loaded) {
                    var bA = $.parseJSON(bu.html.find("#img_list").text());
                    bu.base64.loadImg(bA.right, bA.left)
                }
            } catch (bz) {
            }
            bu.base64.end()
        });
        var bx = [$(window).scrollLeft(), $(window).scrollTop()];
        aj.hide();
        oldEnv = ac;
        ac = bv;
        aq = ax;
        ax = bu;
        bds.comm.cur_disp_query = bv.p("wd");
        bb();
        if (bds && bds.se && bds.se.certification && bds.se.certification.data) {
            bds.se.certification.data = []
        }
        if (pageState == 0) {
            aw(bv)
        }
        bi(function () {
            aI()
        });
        bds.clearReady();
        aj.empty();
        var bw = bu.html;
        if (ap.use_cache_repeatedly) {
            bw = bw.clone()
        }
        bi(function () {
            bw.find("#head_style").children().removeAttr("data-for").appendTo("head")
        });
        bi(function () {
            $.globalEval(bw.find("#head_script").html())
        });
        if (bds.comm && bds.comm.jsversion && bds.comm.jsversion != "004") {
            var bq = ac.buildSyncSearchUrl({jmp: "jsver", _vr: Math.random()});
            bf.replace(bq);
            return
        }
        bi(function () {
            bw.find("#content_script script").each(function (bz, bA) {
                $.globalEval($(bA).html())
            })
        });
        bi(function () {
            var bz = bw.find("#s_tab");
            if (!bz.size()) {
                return
            }
            var bA = $("#s_tab");
            if (bA.size()) {
                bA.replaceWith(bz)
            } else {
                bz.insertBefore(aj)
            }
        });
        var bs = false;
        (function () {
            var bB = bw.find("#con-at");
            var bz = $("#con-at");
            var bC = bz.children().children();
            if (!bC.size()) {
                if (bB.children().size()) {
                    M(aj, bB, "insertBefore")
                }
            } else {
                if (!bB.children().size()) {
                    bz.remove();
                    $(window).trigger("top_result_removed")
                } else {
                    var bA = bB.children().children();
                    if (bC.attr("cq") != bA.attr("cq") || bC.attr("srcid") != bA.attr("srcid") || (bu.force && oldEnv && oldEnv.equals(ac)) || (!ac.p("cq") || !ac.p("srcid")) || (ac.p("_trf") == 1)) {
                        bz.remove();
                        $(window).trigger("top_result_removed");
                        M(aj, bB, "insertBefore")
                    } else {
                        bs = true
                    }
                }
            }
        })();
        var br = bw.find("#container");
        bu.pdc.bindImgLoad(br);
        M(aj, br);
        if (!$("#footer").size()) {
            var by = bw.find("#footer").children();
            M(aj, by)
        }
        bi(function () {
            var bz = new Date().getTime();
            if (bw) {
                $.globalEval(bw.find("#jsMerge").html())
            }
            aO(bu, {jsmergetime: new Date().getTime() - bz})
        });
        if (bds && bds.comm && bds.comm.templateName == bds.comm.resTemplateName) {
            if (bds.comm.seinfo) {
                bds.comm.seinfo.rsv_tpfail = 0
            }
        } else {
            if (bds.comm.seinfo) {
                bds.comm.seinfo.rsv_tpfail = 1
            }
        }
        if (pageState != 0 && bds && bds.util && bds.util.setContainerWidth) {
            bds.util.setContainerWidth()
        }
        document.title = bv.p("wd") + "_百度搜索";
        aj.show();
        aR();
        aO(bu, {domtime: new Date().getTime() - bt});
        aO(bu, {waittime: new Date().getTime() - aV});
        bu.pdc.mark("dom");
        $(window).trigger("swap_dom_ready", [bu, bv]);
        if (window.__lazy_foot_js) {
            setTimeout(function () {
                Y(bv, bu, bt)
            }, 0)
        } else {
            Y(bv, bu, bt)
        }
        if (!bs) {
            window.scrollTo(0, 0)
        } else {
            window.scrollTo(bx[0], bx[1])
        }
        $(window).trigger("scroll");
        swap_wait = false
    }

    function Y(br, bq, bt) {
        var bs;
        if (!bt) {
            bt = 0
        }
        if (bq) {
            bs = bq.html
        }
        bi(function () {
            bj.get(0).f.value = 8
        });
        bi(function () {
            var bu = new Date().getTime();
            if (bq && bq.base64) {
                bq.base64.setDomLoad("left");
                bq.base64.setDomLoad("right")
            }
            aO(bq, {base64time: new Date().getTime() - bu})
        });
        $("#search").find("form").submit(function () {
            var bv = kw;
            kw = $(this).find("[name='wd']");
            var bu = aG.call(this);
            kw = bv;
            return bu
        });
        bi(function () {
            var bu = new Date().getTime();
            bds.doReady();
            aO(bq, {bdstime: new Date().getTime() - bu})
        });
        bi(function () {
            var bu = new Date().getTime();
            if (bs) {
                $.globalEval(bs.find("#ecomScript").html())
            }
            aO(bq, {ecomtime: new Date().getTime() - bu})
        });
        bi(function () {
            var bu = new Date().getTime();
            if (bds.se.tools) {
                if (U) {
                    clearTimeout(U)
                }
                U = setTimeout(function () {
                    bds.se.tools()
                }, 600)
            }
            if (bds && bds.se && bds.se.certification && bds.se.certification.build) {
                if (am) {
                    clearTimeout(am)
                }
                am = setTimeout(function () {
                    if ($(".certification").size() > 0) {
                        bds.se.certification.build.init()
                    }
                }, 1000)
            }
            if (bds && bds.se && bds.se.safeTip) {
                if (Z) {
                    clearTimeout(Z)
                }
                Z = setTimeout(function () {
                    if ($(".unsafe_ico_new").size() > 0) {
                        bds.se.safeTip.init()
                    }
                }, 1200)
            }
            aO(bq, {tiptime: new Date().getTime() - bu})
        });
        bi(function () {
            var bu = new Date().getTime();
            if (aY) {
                clearTimeout(aY)
            }
            aY = setTimeout(function () {
                if (bds.se.login && bds.se.login.setUserInfo) {
                    bds.se.login.setUserInfo()
                }
                if (bds.su && bds.su.U && bds.su.U.urTrigger.init) {
                    bds.su.U.urTrigger.init()
                } else {
                    if ((bds.comm.newindex || bds.comm.username) && bds.su.urStatic) {
                        var bv = $.ajax({
                            dataType: "script",
                            cache: true,
                            url: bds.su.urStatic + "/static/ur/js/ur/urstatic-async-1.4.js"
                        });
                        bv.done(function () {
                            bds.su.U.urTrigger.init()
                        })
                    }
                }
            }, 1400);
            aO(bq, {urtime: new Date().getTime() - bu})
        });
        bi(function () {
            var bu = new Date().getTime();
            window.initResultClickLog();
            aO(bq, {clicktime: new Date().getTime() - bu})
        });
        bi(function () {
            aO(bq, {rtime: new Date().getTime() - bt, used: 1});
            if (bds.comm.seinfo && bq) {
                bds.comm.seinfo.rsv_pstg = bq.type
            }
        });
        bi(function () {
            $(window).trigger("swap_end", [bq, br]);
            bk();
            a1 = new Date().getTime();
            if (bq && bq.pdc) {
                bq.pdc.mark("js");
                bq.pdc.trigger("swap_end")
            }
        })
    }

    function bb() {
        bi(function () {
            $.each(bds.comm.tips, function (bq, br) {
                if (br && br.destroy) {
                    br.destroy()
                }
            });
            $("#c-tips-container").empty();
            bds.comm.tips = []
        });
        bi(function () {
            if (window.app && window.app.dispose) {
                window.app.dispose()
            }
        });
        bi(function () {
            bds.comm.resolveUnloadHandler()
        });
        if (bds && bds.se && bds.se.certification && bds.se.certification.data) {
            bds.se.certification.data = []
        }
        if (bds && bds.se && bds.se.userAction) {
            bds.se.userAction.destroy()
        }
    }

    function al() {
        if (aa && aJ) {
            clearTimeout(aa);
            aa = setTimeout(aJ, aW)
        }
    }

    function w(bq, bs, br) {
        return function (bt) {
            var bu = $.extend({}, bt);
            if (!bq || bq.confirm) {
                return
            }
            bds.comm.cur_query = bq.real_wd;
            if (!bds.comm.supportis && bq) {
                br = bq.pstg || 0
            }
            bq.confirm = true;
            aa = false;
            aJ = null;
            var bx = {};
            if (bs && bs.env) {
                bx.is_referer = bs.env.buildSyncSearchUrl()
            } else {
                bx.is_referer = a8.replace(/\#.*$/, "")
            }
            bx.is_xhr = "1";
            var bv = new V(bn(at.getQueryString()), true);
            if (!bq.env.equals(bv) && !bq.env.clone({wd: bq.prw}).equals(bv)) {
                at.setUrl(bq.env)
            }
            if (!bq.seq) {
                bq.seq = 1
            } else {
                bq.seq++
            }
            if (bq.pdc) {
                if (br != 20 && bds.comm.supportis) {
                    bq.pdc.mark("st")
                }
                if (bq.pdc && bq.pdc.setParam) {
                    bq.pdc.setParam("cus_pstg", br)
                }
                if (bq.force) {
                    bq.pdc.setParam("f4s", 1)
                }
                bq.pdc.trigger("confirm");
                window.PRE_CONN.startTimer()
            }
            $(window).trigger("confirm", [bq, br]);
            var by = "/s?ie=utf-8&csq=" + bq.seq + "&pstg=" + br + (bu.tipConfirm ? "&_cktip=1" : "") + "&mod=2" + (bds.comm.supportis ? "&isbd=1" : "") + "&cqid=" + bq.qid + "&istc=" + (new Date().getTime() - bq.startTime) + "&ver=" + bds.comm.baiduis_verify + "&chk=" + bq.chk + "&isid=" + aD + "&" + bq.env.buildQueryString() + (bq.force ? "&f4s=1" : "") + (typeof x == "function" ? "&_ck=" + x(bq.env.p("wd")) : "");
            if (bds.comm.indexSid) {
                if (/9998_/.test(bds.comm.indexSid) && bf.protocol === "https:") {
                    bds.comm.indexSid = bds.comm.indexSid.replace("9998", "8499")
                }
                by += "&rsv_isid=" + bds.comm.indexSid
            }
            if (true && aZ && aZ.getRsvStatus) {
                try {
                    by += "&rsv_stat=" + aZ.getRsvStatus(bq.env.p("wd"))
                } catch (bw) {
                }
            }
            X.done(function () {
                if (aZ.getStat("rsv_sug6")) {
                    by += "&rsv_sug6=" + aZ.getStat("rsv_sug6");
                    if (bds.comm.seinfo) {
                        bds.comm.seinfo.rsv_sug6 = aZ.getStat("rsv_sug6")
                    }
                }
                if (aZ.getStat("rsv_bp")) {
                    by += "&rsv_bp=" + aZ.getStat("rsv_bp")
                }
            });
            $.ajax({headers: bx, url: by}).done(function (bA) {
                $('#form input[name="rsv_bp"]').val(1);
                var bC = $(bA);
                if (!window._is_pc_direct_sam) {
                    return
                }
                if (bC.length == 1) {
                    var bz, bB;
                    if (bC.attr("http-equiv") && bC.attr("http-equiv") == "Refresh" && bC.attr("id") && bC.attr("id") == "pcDirect") {
                        bz = bC.attr("content") && bC.attr("content").match(/url=(.*)/);
                        bB = bz && bz[1]
                    }
                    if (bB) {
                        bf.replace(bB);
                        return
                    }
                }
            }).fail(function () {
            });
            if (bds.comm.seinfo) {
                bds.comm.seinfo.rsv_prw = encodeURIComponent(kw.val());
                bds.comm.seinfo.rsv_pstg = br;
                bds.comm.seinfo.rsv_svoice = window.__supportvoice ? "1" : "0"
            }
            aT();
            aD = bq.qid;
            X.done(function () {
                if (br == 20) {
                    aZ.updateInitData()
                } else {
                    if (br == 22) {
                        if (window.__sug_history_mod == 13) {
                            aZ.updateInitData()
                        }
                    } else {
                        if (!bds.comm.supportis) {
                            if (br >= 0 && br <= 5) {
                                aZ.updateInitData()
                            }
                        }
                    }
                }
                aZ.clearStat()
            })
        }
    }

    $(window).on("indexOff", function (br, bq) {
        X.done(function () {
            a0(bq.p("wd"))
        })
    });
    if (at.support() && au.attr("target") != "_blank") {
        X.done(function () {
            aZ.setMaxNum(d)
        })
    }
    var az = false, Q;
    var a4 = false;
    bj.mousedown(function () {
        a4 = false
    }).delegate("a,input", "focus", function () {
        a4 = false
    });
    function aG(br) {
        if (!at.support()) {
            return true
        }
        if (a4) {
            return false
        }
        a4 = true;
        kw.blur();
        a0("");
        if ($(this).attr("target")) {
            return true
        }
        az = true;
        Q = setTimeout(function () {
            az = false
        }, 1000);
        try {
            if (!$.trim(kw.val())) {
                bf.href = "/";
                return false
            }
            var bu = new V();
            var bt = $(this).serializeArray(), bq;
            for (bq = 0; bq < bt.length; bq++) {
                bu.p(bt[bq].name, bt[bq].value)
            }
            bu.p("wd", kw.val());
            if (bu.p("nojc")) {
                bu.p("nojc", "")
            }
            if (ax) {
                if ((bu.equals(ax.env.clone({wd: ax.real_wd}))) && !ax.force) {
                    w(ax, aq, 22)();
                    ax.force = true;
                    a0("");
                    N(ax);
                    return false
                }
                if (ax.env.p("rsv_spt")) {
                    bu.p("rsv_spt", ax.env.p("rsv_spt"))
                }
            }
            at.submit(bu, !!br)
        } catch (bs) {
        }
        return false
    }

    var aP = {};

    function u(bs) {
        var bt = {force: false, no_predict: false, use_cache: false, shouldShow: true, pstg: -1};
        if (bs) {
            $.extend(bt, bs)
        }
        var bv = bt.env, bx = bt.force, bw = bt.no_predict, br = bt.shouldShow, bu = bt.use_cache, bq;
        if (!bv || !bv.p("wd") || !bv.hashCode()) {
            return
        }
        if ((av || Cookie.get("ISSW") == 1) && !bx && bw) {
            return
        }
        if ((E || Cookie.get("ISSW") == 1) && !bx && !bw) {
            return
        }
        if (bu && (bq = l.hasCache(bv, {loaded: true}))) {
            if (br) {
                if (!ax || !bq.env.clone({wd: bq.real_wd}).equals(ax.env.clone({wd: ax.real_wd}))) {
                    bq.force = bt.force;
                    bq.no_predict = bt.no_predict;
                    bq.pdc.init();
                    if (bq.force) {
                        bq.pdc.mark("st");
                        if (window.bds && bds.comm && !bds.comm.supportis) {
                            bq.pdc.mark("net");
                            bq.pdc.setParam("cus_hitpreload", 1)
                        }
                    }
                    aU(bv, bq)
                }
                a0((bt.no_predict && bt.pstg != 6) ? "" : bq.real_wd);
                N(bq)
            }
            return
        }
        if (bx && br && bw) {
            t()
        }
        bq = {
            env: bv,
            no_predict: bw,
            shouldShow: br,
            loaded: false,
            force: bx,
            startTime: new Date().getTime(),
            log: {
                ctime: new Date().getTime() - a1,
                wd: bv.p("wd"),
                ntime: 0,
                stat: 0,
                used: 0,
                rtime: 0,
                utime: (bx ? new Date().getTime() - a1 : 0),
                res: 0
            }
        };
        bq.pdc = aF(bq);
        if (bt.pstg > 0) {
            bq.pstg = bt.pstg
        }
        if (bq.force) {
            bq.pdc.mark("st")
        }
        bq.base64 = isbase64(bq.pdc);
        a9.push(bq.log);
        D++;
        if (bx && br && bw) {
            if (window.__sam_backup_request && !bt.is_backup_request) {
                bq.backup_request_timeout = setTimeout(function () {
                    var by = $.extend({}, bs);
                    by.env = by.env.clone();
                    by.env.p("rsv_bak", "1");
                    by.is_backup_request = true;
                    u(by)
                }, 3000)
            }
        }
        g(bq)
    }

    function aN() {
        var bq = [];
        if (an) {
            bq = $.map(an.slice(0, 2), function (br) {
                return br.value
            })
        }
        return bq.join("\t")
    }

    function bo(bq) {
        J();
        bf.replace(bq.buildSyncSearchUrl())
    }

    var H, K;

    function ak(br, bq) {
        if (!bq) {
            av = true;
            if (H) {
                clearTimeout(H);
                H = false
            }
            H = setTimeout(function () {
                av = S
            }, br)
        } else {
            E = true;
            if (K) {
                clearTimeout(K);
                K = false
            }
            K = setTimeout(function () {
                E = be
            }, br)
        }
    }

    function g(by) {
        var bx;
        var bz = by.env;
        var bv = {};
        var bq;
        if (ax && ax.env) {
            bv.is_referer = ax.env.buildSyncSearchUrl()
        } else {
            bv.is_referer = a8.replace(/\#.*$/, "")
        }
        $.extend(bv, bz.buildHeaders());
        bv.is_xhr = "1";
        if (window.bds && bds.comm && bds.comm.cur_query) {
            bz.p("bs", bds.comm.cur_query)
        } else {
            bz.p("bs", "")
        }
        if (window.bds && bds.comm && bds.comm.cur_disp_query) {
            bv.is_pbs = encodeURIComponent(bds.comm.cur_disp_query)
        }
        var br = "ie=utf-8" + (bds.comm.newindex ? "&newi=1" : "") + (ar.sid ? "&sid=" + encodeURIComponent(ar.sid) : "") + (ar.tnp ? "&tnp=" + encodeURIComponent(ar.tnp) : "") + "&mod=" + (by.no_predict || !bds.comm.supportis ? "1" : "11") + (bds.comm.supportis ? "&isbd=1" : "") + "&isid=" + aD + "&" + bz.buildQueryString() + "&rsv_sid=" + bds.comm.indexSid + "&_ss=1&clist=" + encodeURIComponent(l.getCacheList()) + "&hsug=" + encodeURIComponent(aN()) + (by.force ? "&f4s=1" : "") + "&csor=" + m(kw.get(0));
        if (by.pstg) {
            br += "&pstg=" + by.pstg
        }
        var bs = "/s?" + br;
        bs += "&_cr1=" + ay(bs);
        if (!by.no_predict) {
            bq = l.find(function (bC) {
                if (!bC.loaded && !bC.no_predict) {
                    return true
                }
            });
            for (bx = 0; bx < bq.length; bx++) {
                l.deleteCache(bq[bx])
            }
        }
        if (by.no_predict && !by.force) {
            bq = l.find(function (bC) {
                if (bC.force && bz.equals(bC.env)) {
                    return true
                }
            });
            if (bq.length > 0) {
                return
            }
        }
        if (by.force && by.shouldShow) {
            var bA = false;
            var bt = new Date().getTime();
            bq = l.find(function (bD) {
                var bC = bz.equals(bD.env);
                if (!bD.loaded && !bD.no_predict && bC && bD !== by) {
                    bD.shouldShow = false
                }
                if (!bD.loaded && bD.no_predict && bD.force && bC && bD !== by) {
                    bD.shouldShow = bD.shouldShow || by.shouldShow;
                    if (bD.startTime && bt - bD.startTime < 2000) {
                        bA = true
                    }
                    if (!window.__sam_backup_request) {
                        bA = true
                    }
                }
                if (!bD.loaded && !bC) {
                    return true
                } else {
                    bD.pdc.mark("st")
                }
            });
            if (bA) {
                return
            }
            for (bx = 0; bx < bq.length; bx++) {
                l.deleteCache(bq[bx])
            }
        }
        var bu = function (bE, bD, bG) {
            if (bD == 0) {
                bw(bE, bG);
                if (by.pdc) {
                    by.pdc.setParam("cus_srv", bds.se.mon.srvt);
                    by.pdc.setParam("bsi", Cookie.get("__bsi"))
                }
            } else {
                if (bD == 1) {
                    try {
                        var bC = new Date() * 1;
                        by.b64ildata = $.parseJSON(bE);
                        by.base64.ilparseTime = new Date() * 1 - bC;
                        if (by === ax) {
                            by.base64.inline(by.b64ildata);
                            by.base64.ilrenderTime = new Date() * 1 - bC
                        }
                        $(by).one("swap_begin", function () {
                            this.base64.inline(this.b64ildata, this.html.get(0))
                        })
                    } catch (bF) {
                    }
                } else {
                    if (bD == 2) {
                        if (by.base64) {
                            by.base64.ilsum = bE
                        }
                    }
                }
            }
        };
        var bw = function (bY, bM) {
            if ((bM && bM.status == "302") || (bY && bY.indexOf("<div>") > 10)) {
                if (by.force) {
                    bo(bz)
                } else {
                    l.deleteCache(by)
                }
                return
            }
            aO(by, {ntime: new Date().getTime() - by.startTime, res: 1});
            var bV;
            var bX = "<!--data-->";
            var bJ = bY.indexOf(bX);
            if (bJ != -1) {
                bV = $(bY.substr(0, bJ));
                by.html = bY.substr(bJ + bX.length);
                if (window.__dom_pre_parse && bV.find("#__need_parse_dom").html() == "1") {
                    by.html = $(by.html)
                }
                try {
                    var bL = $.parseJSON(bV.find("#img_list").text());
                    by.base64.loadImg(bL.right, bL.left);
                    by.base64_loaded = true
                } catch (bU) {
                }
                try {
                    a7(bV.find("#limg_list").text())
                } catch (bU) {
                }
            } else {
                bV = by.html = $(bY)
            }
            var bE = parseInt(bV.find("#__status").eq(0).html());
            var bC = parseInt(bV.find("#__switchtime").eq(0).html());
            var bF = parseInt(bV.find("#__redirect").eq(0).html());
            var bS = {};
            try {
                bS = $.parseJSON(bV.find("#__sugPreInfo:eq(0)").html() || "{}") || {}
            } catch (bU) {
            }
            by.real_wd = bV.find("#__real_wd").eq(0).text();
            by.real_wd_org = bV.find("#__real_wd_org").eq(0).text();
            var bP = false;
            if ((bz.p("wd") == a3(kw.val()) || by.force) && by.shouldShow) {
                bP = true
            }
            if (by.real_wd) {
                by.prw = bz.p("wd");
                bz.p("wd", by.real_wd)
            }
            var bG = bV.find("#__queryId").html();
            var bD = bV.find("#__querySign").html();
            by.querySign = bD;
            aO(by, {stat: (bE ? bE : 0)});
            if (bds.comm.isDebug) {
                $("#isDebugInfo").html(bV.find("#__isDebugInfo").html())
            }
            if (bG) {
                by.qid = bG
            }
            var bW = bV.find("#__chk").html();
            by.chk = bW ? bW : 0;
            if (window._is_pc_direct_sam && bV.length == 1) {
                var bN, bI;
                if (bV.attr("http-equiv") && bV.attr("http-equiv") == "Refresh" && bV.attr("id") && bV.attr("id") == "pcDirect") {
                    bN = bV.attr("content") && bV.attr("content").match(/url=(.*)/);
                    bI = bN && bN[1]
                }
                if (bI) {
                    bf.replace(bI);
                    return
                }
            }
            if (!bY || (!bG && !bC && !bF && !bE) || (!bD && by.force)) {
                if (by.force) {
                    bz.p("__eis", 1);
                    bz.p("__eist", bY ? bY.length : 0);
                    bz.p("real_wd", by.real_wd);
                    bo(bz);
                    return
                } else {
                    l.deleteCache(by);
                    return
                }
            }
            if (bC > 0) {
                ak(bC * 1000, !by.no_predict)
            }
            if (bE == -11) {
                var bR = l.getCacheBySign(bD);
                if (!bR) {
                    u({env: by.env.clone({wd: by.real_wd}), force: by.force, use_cache: false, no_predict: true});
                    l.deleteCache(by);
                    return
                }
                bR.force = by.force;
                aA();
                a0(bR.real_wd);
                N(bR);
                l.deleteCache(by);
                by = bR;
                if (!ax || by.real_wd != ax.real_wd) {
                    bP = true
                }
            } else {
                if (bE < 0) {
                    if (bF == 1 && by.force) {
                        aO(by, {redirect: 1});
                        bo(bz);
                        return
                    }
                    f();
                    l.deleteCache(by);
                    if (bE == -12 && bS && bS.wait_time > 0) {
                        var bO = bS.wait_time - (new Date().getTime() - (by.startTime || aV));
                        bO = Math.max(bO, 0);
                        var bK = $.trim(kw.val());
                        var bQ = new V({pn: "", wd: bK});
                        T = setTimeout(function () {
                            u({env: bQ, force: false, use_cache: true, no_predict: true, shouldShow: true, pstg: 6});
                            B = false
                        }, bO)
                    }
                    return
                } else {
                    if (bE > 0) {
                        l.deleteCache(by);
                        return
                    }
                }
            }
            var bH = l.find(function (bZ) {
                if (!bZ.loaded && bZ !== by && bZ.no_predict && bz.equals(bZ.env)) {
                    if (bZ.shouldShow) {
                        bP = true
                    }
                    if (bZ.force) {
                        by.force = true;
                        by.no_predict = true
                    }
                    return true
                }
            });
            for (var bT = 0; bT < bH.length; bT++) {
                l.deleteCache(bH[bT])
            }
            if (by.backup_request_timeout) {
                clearTimeout(by.backup_request_timeout)
            }
            by.loaded = true;
            if (!bds.comm.supportis && !bP) {
                return true
            }
            if (bP && by !== ax || by.force) {
                aA();
                by.shouldShow = false;
                if (bE == -11) {
                    by.pdc.init()
                } else {
                    by.pdc.mark("net")
                }
                aU(bz, by)
            }
        };
        var bB;
        bB = $.ajax({dataType: "parts", url: bs, headers: bv, delimiter: "</*3*/>"});
        bB.parts(function (bD, bC, bE) {
            bu(bD, bC, bB)
        });
        bB.fail(function (bD, bC) {
            if (!window.__sam_noloading && by.force && by.shouldShow && bC != "abort" && by.env) {
                bf.replace(by.env.buildSyncSearchUrl() + "&rsv_jmp=fail")
            }
            l.deleteCache(by)
        });
        by.xhr = bB;
        l.addCache(by)
    }

    function bk() {
        var bq;
        a9 = [];
        D = 0;
        az = false;
        clearTimeout(Q)
    }

    $(window).on("swap_end", function (br, bq) {
        if (!bq) {
            aT()
        }
    });
    function s() {
        if (window.index_off) {
            window.index_off()
        }
        if (index_kw[0] !== bg[0]) {
            index_kw.val("")
        }
        kw = bg;
        pageState = 1;
        bds.comm.ishome = 0;
        bds.comm.cur_query = bds.comm.query;
        ac = new V();
        ax = {env: ac, real_wd: bds.comm.query, force: true, confirm: true};
        X.done((function (bq) {
            return function () {
                o.start()
            }
        })());
        $(window).trigger("index_off");
        bds.util.setContainerWidth();
        bi(function () {
            $(window).trigger("swap_dom_ready")
        });
        if (window.__lazy_foot_js) {
            setTimeout(function () {
                Y()
            }, 0)
        } else {
            Y()
        }
    }

    function aw(bq) {
        if (window.index_off) {
            window.index_off()
        }
        if (index_kw.get(0) !== bg.get(0)) {
            index_kw.val("");
            bg.val(bq.p("wd"))
        }
        kw = bg;
        pageState = 1;
        bds.comm.ishome = 0;
        X.done(function () {
            if (aM !== o) {
                aM.stop();
                o.hide();
                o.setKey(bq.p("wd"));
                o.start()
            }
        });
        bds.util.setContainerWidth();
        $(window).trigger("index_off", bq)
    }

    if (window.__click_input_swap) {
        kw.click(function () {
            if (pageState == 0) {
                var bq = new V({pn: "", wd: kw.val()});
                aw(bq)
            }
        })
    }
    at.init();
    $(function () {
        var bq = $("script").last();
        var br = $("head");
        aI = function () {
            bq.nextAll().not("[data-for]").not("#passport-login-pop").remove();
            br.find("*").not("[data-for]").not("meta").not("title").not("script[async]").not('link[href*="passport"]').remove()
        }
    });
    if (bds.comm.resultPage) {
        s()
    }
    z.delegate("#s_tab a", "mousedown", function () {
        setHeadUrl(this)
    }).delegate("#s_tab a", "focusin", function () {
        setHeadUrl(this)
    });
    aj.delegate("#page strong+a,#page a.n", "mouseover", function () {
        u({
            env: new V(bn($(this).attr("href"))),
            force: false,
            use_cache: true,
            no_predict: true,
            shouldShow: false,
            pstg: 4
        })
    });
    var ah, af;
    var j, ae, R;

    function a6() {
        ah = false;
        af = false;
        ae = [];
        clearTimeout(R);
        R = false
    }

    function q(bq) {
        if (!ah) {
            ah = {x: bq.pageX, y: bq.pageY}
        }
        j = {x: bq.pageX, y: bq.pageY};
        if (!af && ah.x != bq.pageX && ah.y != bq.pageY) {
            af = true;
            ae = [ah];
            aB()
        }
    }

    function aB() {
        ae.push(j);
        var bs = ae.length;
        if (Math.pow((j.x - ah.x), 2) + Math.pow((j.y - ah.y), 2) >= Math.pow(y, 2) || bs * a >= b) {
            var bu = [];
            bu.push(document.documentElement.clientWidth || window.innerWidth, document.documentElement.clientHeight || window.innerHeight, document.body.scrollLeft || document.documentElement.scrollLeft, document.body.scrollTop || document.documentElement.scrollTop);
            for (var br in ae) {
                bu.push(ae[br].x, ae[br].y)
            }
            var bq = aZ;
            var bv = !bds.comm.supportis ? 2 : 1;
            if (bv && bq && bq.data() && bq.data()[0] && bq.visible()) {
                var bt = new V().clone({wd: bq.data()[0].value}).h({is_params: {mstl: bu}});
                u({env: bt, force: false, no_predict: true, use_cache: true, shouldShow: false, pstg: 1});
                bv--
            }
            if (bv && bq && bq.data() && bq.data()[1] && bq.visible()) {
                var bt = new V().clone({wd: bq.data()[1].value}).h({is_params: {mstl: bu}});
                u({env: bt, force: false, no_predict: true, use_cache: true, shouldShow: false, pstg: 1});
                bv--
            }
            if (!bds.comm.supportis && bv && $.trim(kw.val()) && (!ax || ax.env.p("wd") != $.trim(kw.val()))) {
                var bt = new V().clone({wd: $.trim(kw.val())}).h({is_params: {mstl: bu}});
                u({env: bt, force: false, no_predict: true, use_cache: true, shouldShow: false, pstg: 1});
                bv--
            }
        } else {
            R = setTimeout(function () {
                aB()
            }, a)
        }
    }

    X.done(function () {
        $(document).mousemove(q)
    });
    $("#u .back_org").click(function () {
        var bq = new Date();
        bq.setTime(new Date().getTime() + 1103760000000);
        Cookie.set("ORIGIN", 2, document.domain, "/", bq);
        if (ac) {
            bf.replace(ac.buildSyncSearchUrl({_r: Math.random()}))
        } else {
            bf.href = "/"
        }
    });
    $(window).scroll((function () {
        var bs = $("#head"), bq = $(window);
        var br = 40;
        var bv;
        var bu = bs.offset().top;
        var bt = function () {
            if (bv) {
                clearTimeout(bv);
                bv = false
            }
            bv = setTimeout(function () {
                var bw = bq.scrollTop();
                if (bw > br + bu) {
                    bv = setTimeout(function () {
                        bs.addClass("s_down");
                        X.done(function () {
                            o.hide()
                        })
                    }, 0)
                } else {
                    if (bw <= br + bu) {
                        bv = setTimeout(function () {
                            bs.removeClass("s_down")
                        }, 0)
                    }
                }
            }, 50)
        };
        bt();
        return bt
    })());
    kw.bind("paste", function (br) {
        if ((window.__disable_index_predict && pageState == 0) || av || E) {
            return
        }
        var bs = this;
        var bq = this.value;
        O = true;
        setTimeout(function () {
            if (bs.value && bs.value != bq) {
                u({
                    env: new V().clone({wd: $.trim(bs.value)}),
                    force: false,
                    use_cache: true,
                    no_predict: true,
                    shouldShow: bds.comm.supportis,
                    pstg: 2
                })
            }
        }, 0)
    })
}
(function (A) {
    var baidu = window.baidu;
    var LOG_CLASS = ["TITLE", "LINK", "IMG", "BTN", "INPUT", "OTHERS"];
    var C_LOG_CLASS = ["btn"];
    var contentLeft, contentRight, contentTop;

    function clickDebug(e) {
    }

    window.initResultClickLog = function () {
        contentLeft = $("#content_left").get(0);
        contentRight = $("#con-ar").get(0);
        contentTop = $("#con-at").get(0);
        if (A.has) {
            var aladdin_tables = $(".result-op").get(), srcid;
            $.each(aladdin_tables, function (i, v) {
                if (srcid = v.getAttribute("srcid")) {
                    A.ids.push([v.id, srcid])
                }
            })
        }
        bindP5()
    };
    $(document).ready(function () {
        bindLogEvent()
    });
    function bindP5() {
        var item, index = (bds.comm.pageNum - 1) * bds.comm.pageSize + 1, leftItems = (contentLeft && contentLeft.children) || [], rightItems = (contentRight && contentRight.children) || [], topItems = (contentTop && contentTop.children) || [], isResult = function (o) {
            return (o.nodeType == 1 && o.className && /\bresult(\-op)?\b/.test(o.className))
        }, isFrame = function (o) {
            return (o.nodeType == 1 && o.className && /\bc\-frame\b/.test(o.className))
        }, setClickData = function (wrap, data) {
            var sData = wrap.getAttribute("data-click") || "{}";
            try {
                var oData = eval("(" + sData + ")");
                sData = $.stringify($.extend(oData, data));
                wrap.setAttribute("data-click", sData)
            } catch (e) {
                clickDebug(e)
            }
        }, bindP5ClickData = function (items) {
            for (var i = 0, l = items.length; i < l; i++) {
                item = items[i];
                if (isResult(item)) {
                    setClickData(item, {p5: index++})
                } else {
                    if (isFrame(item)) {
                        try {
                            var frameItems = item.children[0].children;
                            for (var j = 0, lj = frameItems.length; j < lj; j++) {
                                var frameItem = frameItems[j];
                                if (isResult(frameItem)) {
                                    setClickData(frameItem, {p5: index++})
                                }
                            }
                        } catch (e) {
                            clickDebug(e)
                        }
                    }
                }
            }
            index = (bds.comm.pageNum - 1) * bds.comm.pageSize + 1
        };
        bindP5ClickData(leftItems);
        bindP5ClickData(rightItems);
        bindP5ClickData(topItems)
    }

    function getXPath(node, wrap, path) {
        path = path || [];
        wrap = wrap || document;
        if (node === wrap) {
            return path
        }
        if (node.parentNode !== wrap) {
            path = getXPath(node.parentNode, wrap, path)
        }
        if (node.previousSibling) {
            var count = 1;
            var sibling = node.previousSibling;
            do {
                if (sibling.nodeType == 1 && sibling.nodeName == node.nodeName) {
                    count++
                }
                sibling = sibling.previousSibling
            } while (sibling)
        }
        if (node.nodeType == 1) {
            path.push(node.nodeName.toLowerCase() + (count > 1 ? count : ""))
        }
        return path
    }

    function bindLogEvent() {
        $body = $("body");
        $body.on("mousedown", function (e) {
            var e = window.event || e, t = e.srcElement || e.target, $t = $(t);
            try {
                var $parent = $t, fm, wrap;
                while ($parent.length && !($parent.hasClass("result") || $parent.hasClass("result-op") || $parent.hasClass("xpath-log"))) {
                    $parent = $parent.parent()
                }
                if (!$parent.length) {
                    return
                }
                if ($parent.hasClass("result-op")) {
                    fm = "alop"
                } else {
                    if ($parent.hasClass("result")) {
                        fm = "as"
                    }
                }
                wrap = $parent.get(0);
                var xpath = getXPath(t, wrap);
                if (check(xpath, t, wrap)) {
                    log(xpath, t, wrap, fm)
                }
            } catch (e) {
                clickDebug(e)
            }
        })
    }

    function getType(xpath, t, wrap) {
        var node = t, cs = LOG_CLASS, cl = cs.length, clc = C_LOG_CLASS, clcl = clc.length, xstr = xpath.join(" "), i = 0;
        while (node !== wrap) {
            for (i = 0; i < cl; i++) {
                if ($(node).hasClass("OP_LOG_" + cs[i])) {
                    return cs[i].toLowerCase()
                }
            }
            for (i = 0;
                 i < clcl; i++) {
                if ($(node).hasClass("c-" + clc[i])) {
                    return clc[i]
                }
            }
            node = node.parentNode
        }
        if (/\bh3\d*\b/.test(xstr)) {
            return "title"
        }
        if (/\ba\d*\b/.test(xstr)) {
            if (/\bimg\d*\b/.test(xstr)) {
                return "img"
            }
            return "link"
        }
        if (/\b(input|select|button|textarea|datalist)\d*\b/.test(xstr)) {
            return "input"
        }
        if (/\blabel\d*\b/.test(xstr) && t.getElementsByTagName("input").length > 0) {
            return "input"
        }
        return ""
    }

    function check(xpath, t, wrap) {
        if (A.LOGTOOL) {
            A.LOGTOOL.call(t, xpath, t, wrap);
            return false
        }
        return true
    }

    function log(xpath, t, wrap, fm) {
        if (t.getAttribute("data-nolog") != null) {
            return
        }
        var type = getType(xpath, t, wrap);
        if (!type) {
            return false
        }
        if (type == "title" && !/\ba\d*\b/.test(xpath)) {
            return false
        }
        var nourl = "http://nourl.ubs.baidu.com";
        var mu = wrap.getAttribute("mu") || nourl;
        if (mu == nourl) {
            var h3 = wrap.getElementsByTagName("h3");
            if (h3 && h3[0]) {
                var a = h3[0].getElementsByTagName("a");
                mu = (a && a[0]) ? a[0].href : mu
            }
        }
        var l = xpath.length, url, p = t, srcid = wrap.getAttribute("srcid");
        var title = "";
        var tag = t.nodeType == 1 ? t.tagName.toLowerCase() : "";
        if (type == "input") {
            if (/input|textarea/.test(tag)) {
                title = t.value;
                if (t.type && t.type.toLowerCase() == "password") {
                    title = ""
                }
            } else {
                if (/select|datalist/.test(tag)) {
                    if (t.children.length > 0) {
                        var index = t.selectedIndex || 0;
                        title = t.children[index > -1 ? index : 0].innerHTML
                    }
                } else {
                    title = t.innerHTML || t.value || ""
                }
            }
        } else {
            if (tag == "img") {
                title = t.title
            }
            if (!title) {
                while (l > 0) {
                    l--;
                    if (/^a\d*\b/.test(xpath[l])) {
                        url = p.href;
                        title = p.innerHTML;
                        if (p.getAttribute("data-nolog") != null) {
                            return
                        }
                        break
                    } else {
                        if (p.className && (/OP_LOG_/.test(p.className))) {
                            title = p.innerHTML;
                            break
                        }
                        p = p.parentNode
                    }
                }
            }
        }
        title = $.trim(title);
        if (!url || url.slice(-1) === "#" || !(/^http/.test(url))) {
            url = mu
        }
        var data = {
            rsv_xpath: xpath.join("-") + "(" + type + ")",
            title: title,
            url: url,
            rsv_height: wrap.offsetHeight,
            rsv_width: wrap.offsetWidth,
            rsv_tpl: wrap.getAttribute("tpl")
        };
        var rewritedatakey = {url: 1, title: 1};
        if (wrap.id && wrap.id.match(/^\d+$/)) {
            data.p1 = wrap.id
        }
        if (srcid) {
            data.rsv_srcid = srcid
        }
        var ext_data, attr, is_fm_null;
        p = t;
        do {
            if (p.getAttribute("data-nolog") != null) {
                return
            }
            if (ext_data = p.getAttribute("data-click")) {
                try {
                    ext_data = (new Function("return " + ext_data))();
                    for (attr in ext_data) {
                        if (attr == "fm" && ext_data.fm === null) {
                            is_fm_null = true
                        }
                        if (ext_data.hasOwnProperty(attr) && ((typeof data[attr] == "undefined") || rewritedatakey[attr])) {
                            data[attr] = ext_data[attr]
                        }
                    }
                } catch (e) {
                    clickDebug(e)
                }
            }
            p = p.parentNode
        } while (p && p !== wrap.parentNode);
        for (var i in data) {
            if (data[i] === null) {
                delete data[i]
            }
        }
        if (type == "title") {
            if ("mu" in data) {
                delete data.mu
            }
        } else {
            if (!data.mu) {
                data.mu = mu
            }
        }
        if (is_fm_null) {
            if ("fm" in data) {
                delete data.fm
            }
        } else {
            if (type == "input") {
                data.fm = "beha";
                data.url = nourl
            }
            if (!data.fm) {
                data.fm = fm
            }
            if (!data.fm) {
                return
            }
        }
        window.c(data)
    }
})(window.bds.aladdin);
for (ai in al_arr) {
    al_arr[ai]()
}
$(document).ready(function () {
    var a;
    $(document).on("click", ".t>a,.op-se-listen-recommend", function (g) {
        g = window.event || g;
        var d = $("#wrapper_wrapper"), b = $(this).closest(".c-container"), f = b.length ? b.find(".c-recommend").eq(0) : [];
        if (!g.ctrlKey && f.length && f.css("display") === "none") {
            a = setTimeout(function () {
                d.find(".c-recommend").hide();
                f.show()
            }, 150)
        }
    });
    $(window).on("swap_begin", function () {
        this.clearTimeout(a)
    })
});
window.onunload = function () {
};
function addEV(d, b, a) {
    if (window.attachEvent) {
        d.attachEvent("on" + b, a)
    } else {
        if (window.addEventListener) {
            d.addEventListener(b, a, false)
        }
    }
}
bds.se.openime = function (a) {
    if (!window.bdime) {
        $.ajax({
            cache: true,
            dataType: "script",
            url: "https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/plugins/ime_733231c5.js",
            success: function () {
                if (a) {
                    openIme.set("py", true)
                }
            }
        })
    } else {
        openIme.set("py", true)
    }
};
(function () {
    $(window).on("load", function () {
        if (window.pageState === 0 && !(bds && bds.comm && bds.comm.personalData && parseInt(bds.comm.personalData.imeSwitch.value, 10))) {
            if (bds.se.bear) {
                bds.se.bear.showHomeBear()
            }
        }
    });
    if (/\bbdime=[12]/.test(document.cookie)) {
        bds.se.openime(false)
    } else {
        $(window).one("swap_end", function () {
            var a = function () {
                var b = "";
                if (0 && bds.comm.newad && bds.comm.newad == 1) {
                    b = $("<span class=\"shouji\"><a href=\"http://www.nuomi.com/?cid=jgytext\" target=\"_blank\" onmousedown=\"return ns_c({'fm':'behs','pj_name':'bdyx_right_link','tab':'bdnuomi'})\">糯米吃货节，地道美食5.17元起！</a></span>");
                    if (b) {
                        if (bds.comm.containerSize === "s") {
                            b.hide()
                        }
                        b.insertAfter("#mHolder");
                        $(window).on("container_resize", function (f, d) {
                            var g = $("#mHolder").nextAll(".shouji").eq(0);
                            if (g.length) {
                                if (g.css("display") === "none" && d === "l") {
                                    g.show()
                                }
                                if (d === "s") {
                                    g.hide()
                                }
                            }
                        })
                    }
                } else {
                    if (bds.comm.upn && bds.comm.upn.os === "windows") {
                        if (bds.comm.upn.browser !== "baiduclient") {
                            b = $("<span class=\"shouji\"><a href=\"http://j.br.baidu.com/v1/t/full/p/mini/tn/10000011/ch_dl_url\" onmousedown=\"return ns_c({'fm':'behs','pj_name':'bdyx_right_link','tab':'bdclient'})\">添加百度到桌面，搜索更便捷！</a></span>")
                        }
                        if (b) {
                            if (bds.comm.containerSize === "s") {
                                b.hide()
                            }
                            b.insertAfter("#mHolder");
                            $(window).on("container_resize", function (f, d) {
                                var g = $("#mHolder").nextAll(".shouji").eq(0);
                                if (g.length) {
                                    if (g.css("display") === "none" && d === "l") {
                                        g.show()
                                    }
                                    if (d === "s") {
                                        g.hide()
                                    }
                                }
                            })
                        }
                    }
                }
            };
            if (bds.se.bear) {
                bds.se.bear.showResultBear(a)
            }
        })
    }
})();
if (bds && bds.comm && !bds.comm.containerSize) {
    bds.comm.containerSize = "s"
}
bds.util.setContainerWidth = function () {
    var f = $("#container"), a = $("#wrapper"), b = bds.util.getWinWidth();
    var d = bds.comm.containerSize;
    if (b < 1217) {
        f.removeClass("container_l container_xl").addClass("container_s");
        a.removeClass("wrapper_l").addClass("wrapper_s");
        bds.comm.containerSize = "s"
    } else {
        if (b >= 1217) {
            f.removeClass("container_s container_xl").addClass("container_l");
            a.removeClass("wrapper_s").addClass("wrapper_l");
            bds.comm.containerSize = "l"
        } else {
            return
        }
    }
    if (d != bds.comm.containerSize) {
        $(window).trigger("container_resize", bds.comm.containerSize)
    }
};
bds.util.setFootStyle = function () {
    this.init();
    this.bindEvent()
};
$.extend(bds.util.setFootStyle.prototype, {
    ie6: bds.comm.upn && bds.comm.upn.browser === "msie" && bds.comm.upn.ie == 6, init: function () {
        var a = $("#foot");
        a.addClass("foot_fixed_bottom");
        if (this.ie6) {
            var b = $(window).height() + $(window).scrollTop() - a.outerHeight(true);
            a.css("top", b + "px")
        }
    }, setFixedIe6: function () {
        var a = $("#foot");
        var b = $(window).height() + $(window).scrollTop() - a.outerHeight(true);
        a.css("top", b + "px")
    }, bindEvent: function () {
        var a = this;
        if (a.ie6) {
            $(window).on("resize.setFootStyle, scroll.setFootStyle", function () {
                a.setFixedIe6()
            })
        }
    }
});
var bds = bds || {};
bds.se = bds.se || {};
bds.se.tip = bds.se.tip || {};
bds.comm.tipZIndex = 220;
bds.comm.tips = [];
bds.se.tip = function (a) {
    this.init = function () {
        this.op = {
            target: a.target || {},
            mode: a.mode || "over",
            title: a.title || null,
            content: a.content || null,
            uncontrolled: (a.uncontrolled) ? true : false,
            arrow: {has: 1, offset: 10},
            close: a.close || 0,
            align: a.align || "left",
            offset: {x: 10, y: 20},
            arrowSize: 16
        };
        if (a.arrow) {
            this.op.arrow.has = (a.arrow.has == 0) ? 0 : 1;
            this.op.arrow.offset = (a.arrow.offset >= 0) ? a.arrow.offset : 10
        }
        if (a.offset) {
            this.op.offset.x = (a.offset.x || a.offset.x == 0) ? a.offset.x : 10;
            this.op.offset.y = (a.offset.y || a.offset.y == 0) ? a.offset.y : 20
        }
        this.ext = a.ext || {};
        this.dom = $("<div>", {"class": "c-tip-con"});
        this.visible = false;
        this.rendered = false;
        this.isAuto = (this.op.align === "auto") ? true : false;
        this.bindEvent()
    };
    this.render = function () {
        if (this.op.close) {
            this.enableCloseIcon()
        }
        if (this.op.title) {
            this.setTitle(this.op.title)
        }
        if (this.op.content) {
            this.setContent(this.op.content)
        }
        if (this.op.arrow.has) {
            this.enableArrow()
        }
        $("#c-tips-container").append(this.dom)
    };
    this.bindEvent = function () {
        this.delay = {overIcon: null, outIcon: null, overDom: null, outDom: null};
        if (this.op.mode == "over") {
            var b = this;
            $(b.op.target).on("mouseenter", function () {
                window.clearTimeout(b.delay.outIcon);
                window.clearTimeout(b.delay.outDom);
                b.delay.overIcon = setTimeout(function () {
                    b.show()
                }, 200)
            });
            b.dom.on("mouseenter", function () {
                window.clearTimeout(b.delay.outIcon);
                window.clearTimeout(b.delay.outDom);
                b.delay.overDom = setTimeout(function () {
                    b.show()
                }, 200)
            });
            $(b.op.target).on("mouseleave", function () {
                window.clearTimeout(b.delay.overIcon);
                window.clearTimeout(b.delay.overDom);
                b.delay.outIcon = setTimeout(function () {
                    b.hide()
                }, 200)
            });
            b.dom.on("mouseleave", function () {
                window.clearTimeout(b.delay.overIcon);
                window.clearTimeout(b.delay.overDom);
                b.delay.outIcon = setTimeout(function () {
                    b.hide()
                }, 200)
            })
        } else {
            if (this.op.mode == "none") {
                var b = this;
                b.show()
            }
        }
    };
    this.enableArrow = function () {
        var b = $("<div>", {"class": "c-tip-arrow"}).html("<em></em><ins></ins>").appendTo(this.dom);
        this.arrow = b
    };
    this.enableCloseIcon = function () {
        var d = this;
        var b = $("<div>", {"class": "c-tip-close"}).html("<i class='c-icon c-icon-close'></i>").appendTo(this.dom).click(function () {
            d.hide()
        });
        this.close = b
    };
    this.setTitle = function (b) {
        if (b.nodeType) {
            var d = $("<h3>", {"class": "c-tip-title"}).append(b).appendTo(this.dom)
        } else {
            var d = $("<h3>", {"class": "c-tip-title"}).html(b).appendTo(this.dom)
        }
        this.title = d
    };
    this.setContent = function (d) {
        var b = $("<div>").html(d).appendTo(this.dom);
        this.content = b
    };
    this.setArrow = function (b) {
        if (b) {
            if (b.offset >= 0) {
                this.op.arrow.offset = b.offset
            }
        }
        if (this.op.arrow.has && this.arrow) {
            switch (this.op.align) {
                case"left":
                    this.arrow.css({left: this.op.arrow.offset + "px"});
                    break;
                case"right":
                    this.arrow.css({right: this.op.arrow.offset + 16 + "px"});
                    break;
                default:
                    break
            }
        }
    };
    this.setOffset = function (b) {
        if (b) {
            this.op.offset.x = (b.x || b.x == 0) ? b.x : this.op.offset.x;
            this.op.offset.y = (b.y || b.y == 0) ? b.y : this.op.offset.y
        }
        switch (this.op.align) {
            case"left":
                var d = $(this.getTarget()).offset();
                this.getDom().css({top: d.top + this.op.offset.y + "px", left: d.left - this.op.offset.x + "px"});
                break;
            case"right":
                var d = $(this.getTarget()).offset();
                this.getDom().css({
                    top: d.top + this.op.offset.y + "px",
                    left: d.left + this.op.offset.x + $(this.getTarget()).width() - this.getDom().width() + "px"
                });
                break;
            default:
                break
        }
    };
    this.autoOffset = function (o) {
        var d = {
            w: this.dom.outerWidth(),
            h: this.dom.outerHeight()
        }, m = $(this.getTarget()), n = m.offset(), i = {
            w: m.outerWidth(),
            h: m.outerHeight()
        }, l = $(window), f = l.scrollTop(), k = {w: l.width(), h: l.height()}, b = {left: 0, top: 0}, j = {}, g;
        if ((k.h + f - i.h - n.top) > d.h) {
            b.top = i.h + n.top + this.op.arrowSize / 2;
            if (this.arrow) {
                this.arrow.removeClass("c-tip-arrow-down")
            }
        } else {
            if (n.top - f > d.h) {
                b.top = n.top - d.h - this.op.arrowSize / 2;
                if (this.arrow) {
                    this.arrow.addClass("c-tip-arrow-down")
                }
            } else {
                b.top = i.h + n.top + this.op.arrowSize / 2;
                if (this.arrow) {
                    this.arrow.removeClass("c-tip-arrow-down")
                }
            }
        }
        g = n.left + i.w / 2 - this.op.arrow.offset - this.op.arrowSize / 2;
        b.left = g;
        if (b.left > 0 && (b.left + d.w) > k.w) {
            b.left = n.left + i.w / 2 - d.w + this.op.arrow.offset + this.op.arrowSize / 2;
            j.right = this.op.arrow.offset + this.op.arrowSize;
            j.left = "auto";
            if (b.left < 0) {
                b.left = g;
                j.left = this.op.arrow.offset;
                j.right = "auto"
            }
        } else {
            j.left = this.op.arrow.offset;
            j.right = "auto"
        }
        this.dom.css(b);
        if (this.arrow) {
            this.arrow.css(j)
        }
    };
    this.enable = function () {
    };
    this.disable = function () {
    };
    this.destroy = function () {
    };
    this.show = function () {
        if (!this.visible) {
            this.onShow();
            if (!this.rendered) {
                bds.comm.tips.push(this);
                this.render();
                this.rendered = true
            }
            if (this.isAuto) {
                this.autoOffset()
            } else {
                this.setOffset();
                this.setArrow()
            }
            this.dom.css({"z-index": bds.comm.tipZIndex});
            bds.comm.tipZIndex++;
            this.dom.css({display: "block"});
            this.visible = true
        }
    };
    this.hide = function () {
        if (this.visible) {
            this.dom.css({display: "none"});
            this.onHide();
            this.visible = false
        }
    };
    this.onShow = a.onShow || function () {
    };
    this.onHide = a.onHide || function () {
    };
    this.getTarget = function () {
        return this.op.target
    };
    this.getDom = function () {
        return this.dom
    };
    this.init()
};
bds.event.trigger("se.api_tip_ready");
$(document).mousedown(function (b) {
    b = b || window.event;
    var a = b.target || b.srcElement;
    while (a && a.tagName && a != document.body && a.tagName.toLowerCase() != "html") {
        if (a.className == "c-tip-con") {
            break
        }
        a = a.parentNode
    }
    if (a && a.className != "c-tip-con") {
        $(bds.comm.tips).each(function () {
            if (!this.op.uncontrolled) {
                if (this.op.close) {
                    this.hide()
                }
            }
        })
    }
});
var sethfPos = sethfPos || 0;
(function () {
    var q = "//www.baidu.com/", n = navigator.userAgent.indexOf("MSIE") != -1 && !window.opera, r = Math.random() * 100, w = "百度一下，你就知道", d = "";
    window.fa = function (z) {
        try {
            if (window.sidebar) {
                window.sidebar.addPanel(w, q, "")
            } else {
                if (window.opera && window.print) {
                    z.setAttribute("rel", "sidebar");
                    z.setAttribute("href", q);
                    z.setAttribute("title", w);
                    z.click()
                } else {
                    window.external.AddFavorite(q, w)
                }
            }
        } catch (y) {
        }
    };
    function f(z) {
        if (z) {
            var y = z.parentNode;
            if (y) {
                y.style.marginBottom = "20px";
                y.style.marginTop = "2px"
            }
        }
    }

    if (n) {
        try {
            var x = /se /gi.test(navigator.userAgent);
            var o = /AppleWebKit/gi.test(navigator.userAgent) && /theworld/gi.test(navigator.userAgent);
            var l = /theworld/gi.test(navigator.userAgent);
            var p = /360se/gi.test(navigator.userAgent);
            var a = /360chrome/gi.test(navigator.userAgent);
            var g = /greenbrowser/gi.test(navigator.userAgent);
            var t = /qqbrowser/gi.test(navigator.userAgent);
            var m = /tencenttraveler/gi.test(navigator.userAgent);
            var k = /maxthon/gi.test(navigator.userAgent);
            var u = /krbrowser/gi.test(navigator.userAgent);
            var b = false;
            try {
                b = +external.twGetVersion(external.twGetSecurityID(window)).replace(/\./g, "") > 1013
            } catch (s) {
            }
            if (x || b || o || l || p || a || g || t || m || k || u) {
                var j = sethfPos ? document.getElementById("set_f") : document.getElementById("setf");
                if (j) {
                    j.style.display = "inline";
                    if (sethfPos) {
                        f(j);
                        d = "favorites"
                    }
                }
            } else {
                var i = sethfPos ? document.getElementById("set_h") : document.getElementById("seth");
                if (i) {
                    i.style.display = "inline";
                    if (sethfPos) {
                        f(i);
                        d = "home"
                    }
                }
            }
        } catch (s) {
        }
    } else {
        var j = sethfPos ? document.getElementById("set_f") : document.getElementById("setf");
        if (j) {
            j.style.display = "inline"
        }
        if (sethfPos) {
            f(j);
            d = "favorites"
        }
    }
    if (d && sethfPos) {
        ns_c({fm: "sethf_show", tab: d})
    }
})();
function user_c(i) {
    var g = "", f = "", a = "", b = "", k = encodeURIComponent(window.document.location.href), d = window["BD_PS_C" + (new Date()).getTime()] = new Image(), j = bds && bds.util && bds.util.domain ? bds.util.domain.get("http://nsclick.baidu.com") : "http://nsclick.baidu.com";
    for (v in i) {
        switch (v) {
            case"title":
                a = encodeURIComponent(i[v].replace(/<[^<>]+>/g, ""));
                break;
            case"url":
                a = encodeURIComponent(i[v]);
                break;
            default:
                a = i[v]
        }
        g += v + "=" + a + "&"
    }
    b = "&mu=" + k;
    d.src = j + "/v.gif?pid=201&pj=psuser&" + g + "path=" + k + "&wd=" + f + "&t=" + new Date().getTime();
    return true
}
function initPassV3() {
    bds.se.passv3 = passport.pop.init({
        apiOpt: {
            loginType: 1,
            product: "mn",
            u: window.document.location.href,
            safeFlag: 0,
            staticPage: location.protocol + "//www.baidu.com/cache/user/html/v3Jump.html"
        },
        cache: false,
        tangram: true,
        authsite: ["qzone", "tsina"],
        authsiteCfg: {
            act: "implicit",
            display: "popup",
            jumpUrl: location.protocol + "//www.baidu.com/cache/user/html/xd.html",
            onBindSuccess: function (b, d) {
                var a = decodeURIComponent(d.passport_uname || d.displayname);
                bds.se.login.success(a);
                return false
            }
        },
        onLoginSuccess: function (b) {
            b.returnValue = false;
            var a = b.rsp.data.userName || b.rsp.data.mail || b.rsp.data.phoneNumber;
            bds.se.login.success(a)
        },
        onSubmitStart: function (a) {
        },
        onSubmitedErr: function (a) {
        },
        onSystemErr: function (a) {
        },
        onShow: function () {
        },
        onHide: function () {
            bds.se.login.setSubpro("")
        },
        onDestroy: function () {
        }
    })
}
bds.se.loginCallbackFunc = null;
bds.se.login = (function () {
    var f = "", d = false;
    var j = function () {
        this.setUserInfo();
        var k = this;
        bds.comm.loginAction.push(function (l, m) {
            k.setUserInfo(m)
        })
    }, b = function (l) {
        var k = l || bds.comm.user;
        if (!k) {
            return
        }
        $("#lb").replaceWith('<a href="http://i.baidu.com" class="username">' + escapeHTML(bds.comm.username) + '<i class="c-icon"></i></a>')
    }, a = function (l, k) {
        if (!d) {
            $.getScript(location.protocol + "//passport.baidu.com/passApi/js/uni_login_wrapper.js?cdnversion=" + new Date().getTime(), function () {
                initPassV3();
                d = true;
                bds.se.passv3.setSubpro(f);
                bds.se.loginCallbackFunc = l || function () {
                    window.document.location.reload(true)
                };
                bds.se.passv3.show()
            })
        } else {
            bds.se.passv3.setSubpro(f);
            bds.se.loginCallbackFunc = l || function () {
                window.document.location.reload(true)
            };
            bds.se.passv3.show()
        }
    }, i = function (k) {
        if (!bds.comm) {
            return
        }
        bds.comm.user = k;
        bds.comm.username = k;
        window.bdUser = k;
        bds.se.passv3.hide();
        bds.se.loginCallbackFunc.call(window, 1, k);
        for (var l = 0; l < bds.comm.loginAction.length;
             l++) {
            bds.comm.loginAction[l].call(window, 1, k)
        }
    }, g = function (k) {
        f = k
    };
    return {setUserInfo: b, open: a, success: i, setSubpro: g};
    j()
})();
window._invoke_login = function (b, a) {
    bds.se.login.open(b, a)
};
function isp_hijack(g) {
    var i = document.getElementById("wrapper"), b, a = false, d, f;
    if (!bds.comm.query) {
        a = true
    }
    if (g.stat == 1) {
        b = document.createElement("div");
        b.innerHTML = '<div style="zoom:1;_margin-left:1024px;"><div style="position:relative;_float:left;_margin-left:-1024px;"><div style="width:100%;min-width:1024px;"><div style="border:2px solid #fd9162;zoom:1;overflow:hidden;padding:0 0 6px 12px;"><div style="position:relative;width:100%;*overflow:auto;padding-top:10px;"><div style="height:18px;margin-bottom:6px;"><i class="c-icon" style="width:18px;height:18px;background-position:-168px -72px;"></i><strong style="display:inline-block;margin-left:8px;font-size:14px;color:#666;">百度提示您：</strong></div><span style="display:block;color:#333;text-indent:26px;font-size:13px;">我们发现当前您可能受到异常广告弹窗的影响，通常这是受第三方恶意劫持导致。使用 <a href="http://shadu.baidu.com/landingpage/competing.html?from=10064" target="_blank" style="color:#0000D0;text-decoration:underline">防恶意广告专版杀毒软件</a>，可有效改善您的上网体验，免受恶意广告的困扰。</span><a id="isp-close-btn" style="display:inline-block;width:9px;height:9px;position:absolute;top:6px;right:6px;background:url(../global/img/wsCloseBtn2.png) no-repeat;" href="javascript:void(0);"></a></div></div></div></div></div>';
        if (!a) {
            i.style.position = "relative";
            document.getElementById("u").style.top = 0;
            b.style.margin = "-6px 0 8px 0";
            document.body.insertBefore(b, i)
        } else {
            i.insertBefore(b, i.children[0])
        }
        d = document.getElementById("isp-close-btn");
        f = d.parentNode.getElementsByTagName("a")[0];
        d.onclick = function () {
            if (a) {
                i.removeChild(b)
            } else {
                document.body.removeChild(b);
                i.style.position = "";
                document.getElementById("u").style.top = "4px"
            }
        };
        d.onmousedown = function () {
            ns_c({fm: "behs", tab: "tj_notice", cont: "jcbro", action: "close", area: "topbar"})
        };
        f.onmousedown = function () {
            ns_c({fm: "behs", tab: "tj_notice", cont: "jcbro", action: "click", area: "topbar"})
        };
        ns_c({fm: "behs", tab: "tj_notice", cont: "jcbro", action: "show", area: "topbar"})
    }
}
(function () {
    function a() {
        var d, f = "http://isphijack.baidu.com/index.php?cb=isp_hijack", j = [];
        if (top.location != self.location) {
            try {
                var b = top.document.getElementsByTagName("frame");
                var l = top.document.getElementsByTagName("iframe");
                for (var g = 0; g < b.length; g++) {
                    j.push(b[g].getAttribute("src"))
                }
                for (var g = 0; g < l.length; g++) {
                    j.push(l[g].getAttribute("src"))
                }
            } catch (k) {
            }
            ns_c({fm: "frm", top: encodeURIComponent(top.location.href), furls: encodeURIComponent(j.join("|"))});
            if (j) {
                d = document.createElement("script");
                d.src = f + "&urls=" + encodeURIComponent(j.join("|")) + "&t=" + (+new Date());
                document.body.appendChild(d)
            }
        }
    }

    $(a)
})();
try {
    if (window.console && window.console.log) {
        console.log("一张网页，要经历怎样的过程，才能抵达用户面前？\n一位新人，要经历怎样的成长，才能站在技术之巅？\n探寻这里的秘密；\n体验这里的挑战；\n成为这里的主人；\n加入百度，加入网页搜索，你，可以影响世界。\n");
        console.log("请将简历发送至 %c ps_recruiter@baidu.com（ 邮件标题请以“姓名-应聘XX职位-来自console”命名）", "color:red");
        console.log("职位介绍：http://dwz.cn/hr2013")
    }
} catch (e) {
}
var bds = bds || {};
bds.se = bds.se || {};
bds.se.tool = bds.se.tool || {};
bds.comm.host = {
    bfe: "//www.baidu.com/tools",
    favo: bds.util.domain && bds.util.domain.get ? bds.util.domain.get("http://i.baidu.com") : "http://i.baidu.com",
    share: bds.util.domain && bds.util.domain.get ? bds.util.domain.get("http://bdimg.share.baidu.com/static/api/js/custom/resultshare.js") : "http://bdimg.share.baidu.com/static/api/js/custom/resultshare.js",
    report: "http://jubao.baidu.com",
    koubei: "http://koubei.baidu.com"
};
bds.se.tool = function (item) {
    this.init = function () {
        this.render()
    };
    this.render = function () {
        var ops = eval("(" + item.getAttribute("data-tools") + ")");
        var toolsDom = $("<div>", {"class": "c-tip-menu"});
        var toolsList = $("<ul>");
        var toolsFavo = $("<li>");
        var toolsFavoLink = $("<a>").html("收藏");
        toolsFavoLink.on("mousedown", function () {
            bds.se.tool.favo(ops, item.getAttribute("id"));
            ns_c({fm: "tools", tab: "favo"})
        });
        toolsFavoLink.on("mouseover", function () {
            $(this).css("background-color", "#ebebeb")
        });
        toolsFavoLink.on("mouseout", function () {
            $(this).css("background-color", "#fff")
        });
        toolsFavo.append(toolsFavoLink);
        toolsList.append(toolsFavo);
        var toolsShare = $("<li>");
        var toolsShareLink = $("<a>").html("分享");
        toolsShareLink.on("mousedown", function () {
            bds.se.tool.share(ops, item);
            ns_c({fm: "tools", tab: "share"})
        });
        toolsShareLink.on("mouseover", function () {
            $(this).css("background-color", "#ebebeb")
        });
        toolsShareLink.on("mouseout", function () {
            $(this).css("background-color", "#fff")
        });
        toolsShare.append(toolsShareLink);
        toolsList.append(toolsShare);
        var toolsKoubei = $("<li>").html("<a target=\"_blank\" onmousedown=\"ns_c({'fm': 'tools','tab':'koubei'})\" href=\"" + bds.comm.host.bfe + "?url=" + encodeURIComponent(ops.url) + "&jump=" + encodeURIComponent(bds.comm.host.koubei + "/womc/p/sentry?title=" + encodeURIComponent(ops.title) + "&q=" + encodeURIComponent(bds.comm.query)) + '&key=surl">评价</a>');
        toolsList.append(toolsKoubei);
        var toolsReport = $("<li>").html("<a target=\"_blank\" onmousedown=\"ns_c({'fm': 'tools','tab':'report'})\" href=\"" + bds.comm.host.bfe + "?url=" + encodeURIComponent(ops.url) + "&jump=" + encodeURIComponent(bds.comm.host.report + "/jubao/accu/?title=" + encodeURIComponent(ops.title) + "&q=" + encodeURIComponent(bds.comm.query)) + '&key=surl">举报</a>');
        toolsList.append(toolsReport);
        toolsDom.append(toolsList);
        var tTip = new bds.se.tip({
            target: $(".c-icon", item)[0],
            mode: "none",
            align: "left",
            offset: {x: 33},
            arrow: {has: 1, offset: 30},
            content: toolsDom,
            ext: {category: "tools"}
        });
        tTip.onShow = function () {
            ns_c({fm: "tools", tab: "show"})
        }
    };
    this.init()
};
bds.se.tool.share = function (b, a) {
    this.op = b || {};
    this.init = (function (f, d) {
        $.getScript(bds.comm.host.share, function () {
            $(bds.comm.tips).each(function () {
                if (!this.op.uncontrolled) {
                    this.hide()
                }
            });
            var g = new bds.se.tip({
                target: $(".c-icon", d)[0],
                mode: "none",
                offset: {x: 33},
                arrow: {has: 0},
                close: 1,
                content: '<div class="c-tools-share" style="width:200px;"></div>'
            });
            var i = $(".c-tools-share", g.dom.get(0))[0];
            __bdshare.render({boxEle: i, url: f.url, txt: f.title + " -- 分享自百度搜索"})
        })
    })(this.op, a)
};
bds.se.tool.favo = function (d, b) {
    this.op = d || {};
    this.init = function (k, j) {
        if (k) {
            var f = document.createElement("script");
            var g = bds.comm.host.bfe, i = bds.comm.host.favo;
            f.src = g + "?url=" + encodeURIComponent(k.url) + "&jump=" + encodeURIComponent(i + "/myfavorite/set?irt=1&t=" + encodeURIComponent(k.title) + "&id=" + encodeURIComponent(j) + "&c=bds.se.tool.favo.succ") + "&key=url";
            document.body.appendChild(f)
        }
    };
    if (bds.comm.user) {
        this.init(this.op, b)
    } else {
        if (bds.se.login && bds.se.login.open) {
            var a = this;
            bds.se.login.open(function (g, f) {
                if (g == 1) {
                    a.init(a.op, b)
                }
            })
        }
    }
};
bds.se.tool.favo.succ = function (json) {
    if (json.suc) {
        if (json.status) {
            switch (json.status) {
                case 302:
                    if (bds.se.login && bds.se.login.open) {
                        bds.se.login.open(function (stat, user) {
                            if (stat == 1) {
                                bds.se.tool.favo(eval("(" + $("#" + json.id)[0].getAttribute("data-tools") + ")"), json.id)
                            }
                        })
                    }
                    break;
                case 5:
                    var succContent = '<div class="c-tip-notice">';
                    succContent += '<h3 class="c-tip-notice-fail">收藏失败，请稍后再试</h3>';
                    succContent += "</div>";
                    break
            }
        }
    } else {
        if (json.status) {
            var succContent = '<div class="c-tip-notice">';
            succContent += '<h3 class="c-tip-notice-succ">已收藏至：</h3>';
            succContent += "<ul>";
            switch (json.status) {
                case 2:
                    succContent += '<li class="c-tip-item-succ"><i class="c-icon c-icon-success"></i>个人中心“<a href="http://i.baidu.com/my/collect" target="_blank">我的收藏</a>”</li>';
                    succContent += '<li class="c-tip-item-succ"><i class="c-icon c-icon-success"></i>百度首页“<a href="http://www.baidu.com" target="_blank">我的导航</a>”</li>';
                    break;
                case 3:
                    succContent += '<li class="c-tip-item-succ"><i class="c-icon c-icon-success"></i>个人中心“<a href="http://i.baidu.com/my/collect" target="_blank">我的收藏</a>”</li>';
                    succContent += '<li class="c-tip-item-fail"><i class="c-icon c-icon-fail"></i>百度首页“<a href="http://www.baidu.com" target="_blank">我的导航</a>”</li>';
                    break;
                case 4:
                    succContent += '<li class="c-tip-item-fail"><i class="c-icon c-icon-fail"></i>个人中心“<a href="http://i.baidu.com/my/collect" target="_blank">我的收藏</a>”</li>';
                    succContent += '<li class="c-tip-item-succ"><i class="c-icon c-icon-success"></i>百度首页“<a href="http://www.baidu.com" target="_blank">我的导航</a>”</li>';
                    break;
                default:
                    break
            }
            succContent += "</ul>";
            succContent += "</div>"
        }
    }
    $(bds.comm.tips).each(function () {
        if (!this.op.uncontrolled) {
            this.hide()
        }
    });
    new bds.se.tip({
        target: $(".c-icon", document.getElementById(json.id))[0],
        offset: {x: 33},
        arrow: {has: 0},
        mode: "none",
        arrow: {has: 0},
        close: 1,
        content: succContent
    })
};
var bds = bds || {};
bds.se = bds.se || {};
bds.se.tools = bds.se.tools || {};
bds.se.tools = function () {
    var a = delayHideOnIcon = delayShowOnTip = delayHideOnTip = {};
    $("#container").delegate(".c-tools", "mouseover", function () {
        var b = this;
        window.clearTimeout(delayHideOnIcon);
        window.clearTimeout(delayHideOnTip);
        a = setTimeout(function () {
            var d = 1;
            $(bds.comm.tips).each(function (f) {
                if (this.getTarget() == $(".c-icon", b)[0]) {
                    d = 0;
                    this.show();
                    return false
                }
            });
            if (d) {
                tools = new bds.se.tool(b)
            }
        }, 200)
    }).delegate(".c-tools", "mouseout", function () {
        window.clearTimeout(a);
        window.clearTimeout(delayShowOnTip);
        var b = this;
        delayHideOnIcon = setTimeout(function () {
            $(bds.comm.tips).each(function (d) {
                if (this.getTarget() == $(".c-icon", b)[0]) {
                    this.hide();
                    return false
                }
            })
        }, 200)
    });
    $("#c-tips-container").delegate(".c-tip-con", "mouseover", function () {
        var b = this;
        window.clearTimeout(delayHideOnIcon);
        window.clearTimeout(delayHideOnTip);
        delayShowOnTip = setTimeout(function () {
            $(bds.comm.tips).each(function (d) {
                if (this.getDom().get(0) == b && this.ext.category && this.ext.category == "tools") {
                    this.show();
                    return false
                }
            })
        }, 200)
    }).delegate(".c-tip-con", "mouseout", function () {
        window.clearTimeout(a);
        window.clearTimeout(delayShowOnTip);
        var b = this;
        delayHideOnTip = setTimeout(function () {
            $(bds.comm.tips).each(function (d) {
                if (this.getDom().get(0) == b && this.ext.category && this.ext.category == "tools") {
                    this.hide();
                    return false
                }
            })
        }, 200)
    })
};
bds.se.tools();
var bds = bds || {};
bds.se = bds.se || {};
bds.se.slide = function (n) {
    var g = this, f = {}, i, l, b, j = [], a = 0, m = null, k, d;
    this._default = {
        target: $("#lg"),
        src: "",
        width: 270,
        height: 129,
        offsetLeft: 0,
        isPad: false,
        frames: 103,
        animations: [{
            isAutoPlay: true,
            frame_start: 1,
            frame_end: 30,
            delay: 0,
            duration: 100,
            repeats: 0,
            process_before: function () {
            },
            event_loop: 0,
            process_after: function () {
            }
        }, {
            trigger_type: "click", trigger_duration: 100, trigger_frame: 31, trigger_fn: function () {
            }, frame_start: 32, frame_end: 103, process_before: function () {
            }, process_after: function () {
            }, delay: 0, duration: 100, repeats: 1, event_loop: 0
        }]
    };
    this.timer = [];
    this.otherTimer = [];
    this.op = $.extend({}, g._default, n);
    this.init = function () {
        if (!g.op.src) {
            g.createPlayer();
            return
        }
        g.createDom();
        if (bds.comm.ishome && g.op.target.length) {
            g.initAnimate()
        }
    };
    this.createPlayer = function () {
        var q = g.op.target.find("map"), o = q.length ? q.find("area").eq(0) : "", p = g.op.play;
        if (p) {
            l = $('<img class="logo_player" src="' + p.src + '" style="width:' + p.width + "px; height:" + p.height + "px; position:absolute; top:50%; left:50%; margin-left: -" + (p.width / 2) + "px; margin-top: -" + (p.height / 2) + "px; cursor:pointer;\" onmousedown=\"return c({'tab':'logo_button_click'})\" />").appendTo(g.op.target);
            if (o.length) {
                l.wrap('<a style="position:absolute;top:0;left:50%;width:' + g.op.width + "px;height:" + g.op.height + "px;margin-left:-" + (g.op.width / 2) + 'px;" href="' + o.attr("href") + '" target="' + o.attr("target") + '"></a>');
                if (o.attr("title")) {
                    l.attr("title", o.attr("title"))
                }
            } else {
                l.wrap('<div style="position:absolute;top:0;left:50%;width:' + g.op.width + "px;height:" + g.op.height + "px;margin-left:-" + (g.op.width / 2) + 'px;"></div>')
            }
            l.on(p.trigger_type, function () {
                if (p.trigger_duration) {
                    g.timer.push(window.setTimeout(function () {
                        p.trigger_fn.call(g.op)
                    }, p.trigger_duration))
                } else {
                    p.trigger_fn.call(g.op)
                }
                return false
            })
        }
    };
    this.createDom = function () {
        var s = '<div style="position:absolute;top:0;left:50%;background:url(#{0}) no-repeat #{1};cursor:#{2};width:#{3}px;height:#{4}px;margin-left:-#{5}px;display:none;"></div>', w = g.op.offsetLeft + "px 0", u = g.op.target.find("map"), B = u.length ? u.find("area").eq(0) : "", x = B ? "pointer" : x, z = g.op.animations instanceof Array ? g.op.animations : [g.op.animations], p = g.op.width, y = g.op.height;
        j = z;
        k = p;
        d = y;
        s = $.format(s, g.op.src, w, x, g.op.width, g.op.height, g.op.width / 2);
        if (g.op.target.css("position") === "static") {
            g.op.target.css({position: "relative", width: "100%"})
        }
        g.op.target.append(s);
        i = b = g.op.target.find("div").eq(0);
        if (g.op.play) {
            l = $('<img src="' + g.op.play.src + '" style="width:' + g.op.play.width + "px; height:" + g.op.play.height + "px; position:absolute; top:50%; left:50%; margin-left: -" + (g.op.play.width / 2) + "px; margin-top: -" + (g.op.play.height / 2) + "px; \" onmousedown=\"return c({'tab':'logo_button_click'})\" />").insertAfter(i);
            b = l
        }
        if (g.op.isPad) {
            i.css("background-size", (g.op.width * g.op.frames / 2) + "px " + g.op.height + "px")
        }
        if (B.length) {
            i.wrap('<a href="' + B.attr("href") + '" target="' + B.attr("target") + '"></a>');
            if (B.attr("title")) {
                i.attr("title", B.attr("title"))
            }
        } else {
            i.on("mousedown", function () {
                c({tab: "logo_button_click"})
            })
        }
        for (var r = 0, q = j.length; r < q; r++) {
            var o = j[r];
            var t = o.frame_start;
            w = -((t - 1) * p) + "px 0";
            f[r] = {"background-position": w, cursor: x}
        }
    };
    this.initAnimate = function () {
        if (a >= j.length) {
            return
        }
        var r = j[a], u = r.isAutoPlay, s = r.trigger_type, p = r.trigger_fn, w = r.trigger_duration, t = r.trigger_frame;
        var q = $("#lg area");
        if (q.length && q.attr("onmousedown")) {
            i.on("mousedown", function () {
                return (new Function(q.attr("onmousedown")))()
            })
        }
        m = new Image();
        m.src = g.op.src;
        i.bind("first_animate", function () {
            if (!m.complete) {
                m.onload = o
            } else {
                o()
            }
        });
        if (u) {
            i.trigger("first_animate")
        } else {
            if (s) {
                g.enablePointer();
                b.show().on(s, function () {
                    if (p) {
                        if (t) {
                            g.toPos(t)
                        }
                        p.call(g.op);
                        if (w) {
                            g.timer.push(setTimeout(function () {
                                i.trigger("first_animate")
                            }, w))
                        } else {
                            i.trigger("first_animate")
                        }
                    }
                })
            }
        }
        function o() {
            i.show();
            g.play()
        }
    };
    this.enablePointer = function () {
        if (!(bds.comm.upn && bds.comm.upn.browser === "msie" && bds.comm.upn.ie === "6")) {
            i.css("cursor", "pointer")
        } else {
            alert("pointer")
        }
    };
    this.disablePointer = function () {
        i.css("cursor", "default")
    };
    this.play = function () {
        if (a >= j.length) {
            g.dispose();
            return
        }
        var o = j[a], p = o.process_before;
        g.dispose();
        if (p) {
            p.call(g)
        }
        g.animation()
    };
    this.toPos = function (o) {
        var p = -((o - 1) * k) + "px 0";
        i.css("background-position", p)
    };
    this.animation = function () {
        var u = j[a], x = u.duration, B = u.frame_start, y = u.frame_end, z = u.delay, w = u.repeats, t = u.process_after, q = u.trigger_type, p = B - 1 > 0 ? B - 1 : 0, E = u.event_loop || 0, s = 0;
        var o;
        if (E) {
            o = j[a]
        } else {
            o = a + 1 >= j.length ? j[a] : j[a + 1]
        }
        if (o) {
            var D = o.trigger_type, r = o.trigger_fn, C = o.trigger_duration, F = o.trigger_frame;
            if (D) {
                if (q) {
                    b.off(q)
                }
                if (a < (j.length - 1) || E) {
                    g.enablePointer();
                    b.on(D, function () {
                        if (!E) {
                            a++
                        }
                        if (t) {
                            t.call(g)
                        }
                        if (r) {
                            r.call(g)
                        }
                        if (F) {
                            g.toPos(F)
                        }
                        if (C) {
                            g.dispose();
                            g.timer.push(setTimeout(function () {
                                g.play()
                            }, C))
                        } else {
                            g.play()
                        }
                    })
                } else {
                    g.disablePointer()
                }
            }
        }
        (function () {
            var H = arguments.callee;
            g.timer.push(setTimeout(function () {
                i.css("background-position", -(k * p) + "px 0");
                p++;
                if (p >= y) {
                    g.dispose();
                    s++;
                    if (w !== 0 && s >= w) {
                        p = null;
                        s = null;
                        if (t) {
                            t.call(g)
                        }
                        a++;
                        if (a < j.length) {
                            g.play()
                        }
                        if (E) {
                            a--
                        }
                    } else {
                        p = B - 1 > 0 ? B - 1 : 0;
                        g.timer.push(setTimeout(arguments.callee, x))
                    }
                } else {
                    g.timer.push(setTimeout(arguments.callee, x))
                }
            }, z))
        })()
    };
    this.dispose = function (p) {
        p = p || g.timer;
        for (var q = 0, o = p.length; q < o; q++) {
            window.clearTimeout(p[q])
        }
        p.length = 0
    };
    this.disposeOther = function (p) {
        p = p || g.otherTimer;
        for (var q = 0, o = p.length; q < o; q++) {
            window.clearTimeout(p[q])
        }
        p.length = 0
    };
    this.clear = function () {
        g.dispose();
        g.disposeOther();
        b.off("click").off("hover")
    };
    this.reset = function (o) {
        o = o || 0;
        i.css(f[o])
    };
    this.init()
};
var bds = bds || {};
!bds.se && (bds.se = {});
(function () {
    var q, n, g, a = navigator.userAgent, k, p, r, d, s, o, f, i = bds && bds.util && bds.util.domain ? bds.util.domain.get("http://xiaodu.baidu.com") : "http://xiaodu.baidu.com", m = encodeURIComponent("百小度");
    bds.se.bearVersion = "https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/plugins/bear_9f926169.js";
    try {
        o = window.localStorage
    } catch (l) {
    }
    if (!o) {
        o = {
            setItem: function () {
            }, getItem: function () {
            }
        }
    }
    k = function (B, w, y, x) {
        var u = x || {};
        var z = u.source;
        if ((w || y) && /\$\#.+\#\$/.test(B)) {
            var t = [], C = [];
            if ($.isArray(w)) {
                t = $.extend(true, [], w)
            } else {
                if (w) {
                    t = [w]
                }
            }
            if ($.isArray(y)) {
                C = $.extend(true, [], y)
            } else {
                if (y) {
                    C = [y]
                }
            }
            if (t.length > 0) {
                var z = u.source;
                B = B.replace(/\$\#(.+?)\#\$/g, function (D, F) {
                    var E = t.shift() || F;
                    return '<a data-source="' + (z || "link") + '" data-type="link" data-query="' + encodeURIComponent(E) + '" href="http://www.baidu.com/s?wd=' + encodeURIComponent(E) + '&tn=baiduhome_pg" target="_blank">' + F + "</a>"
                })
            } else {
                if (C.length > 0) {
                    B = B.replace(/\$\#(.+?)\#\$/g, function (D, F) {
                        var E = C.shift() || F;
                        return '<a data-source="' + (z || "link") + '" data-type="link" data-query="' + encodeURIComponent(F) + '" href="' + E + '" target="_blank">' + F + "</a>"
                    })
                }
            }
        }
        return B
    };
    var j = function () {
        $.ajax({
            url: i + "/s",
            data: {
                sample_name: "bear_brain",
                request_query: "百小度",
                request_type: 9,
                request_time: +new Date(),
                request_from: 3,
                source_type: f.source_type || "",
                service_id: f.service_id || "",
                action_type: "tip_close_click"
            }
        })
    };
    r = function (x, B, w) {
        var u = '<div data-link="http://www.baidu.com/s?wd=' + m + '&bd_bear=open&tn=baiduhome_pg" class="bd_bear_home_tip" style="cursor: pointer;' + (w ? "display: none;" : "") + '">            <span class="bd_bear_home_tip_arrow"></span>            <span class="bd_bear_home_tip_close"></span>            <span class="bd_bear_home_tip_content">#{content}</span>        </div>', z, C = $(window), t;
        if (!B || !B.length) {
            return
        }
        z = $(u.replace("#{content}", x || "")).appendTo(B);
        B.addClass("bds_bear_home_tip_show");
        t = function () {
            var D = $(".bd_bear_home_tip");
            if (!D.size()) {
                C.off("resize", t);
                return
            }
            if (C.width() < 1094 && D.css("width") == "225px") {
                D.css("width", 173)
            } else {
                if (C.width() >= 1094 && D.css("width") == "173px") {
                    D.css("width", 225)
                }
            }
        };
        t();
        C.on("resize", t);
        if (w) {
            setTimeout(function () {
                z.fadeIn(function () {
                    var D = $('<div class="bd_bear_home_guide_cursor"></div>').appendTo(B);
                    setTimeout(function () {
                        D.animate({bottom: -6, left: 14});
                        setTimeout(function () {
                            B.remove();
                            changeUrl("wd=" + m + "&bd_bear=guide")
                        }, 1000)
                    }, 1000)
                })
            }, 1500)
        } else {
            var y = function () {
                z.remove();
                B.removeClass("bds_bear_home_tip_show bd_bear_home_guide")
            };
            z.click(function () {
                window.open(z.data("link"));
                window.ns_c && ns_c({bear_log: 1, from: "bg_home", query: decodeURIComponent(m)});
                y()
            });
            $(".bd_bear_home_bear_head").click(y);
            z.find(".bd_bear_home_tip_close").click(function (D) {
                D.stopPropagation();
                y();
                j()
            });
            z.delegate("a", "click", function (E) {
                var D = $(E.target);
                window.ns_c && ns_c({
                    bear_log: 1,
                    query: decodeURIComponent(D.data("query")),
                    type: D.data("type"),
                    source: decodeURIComponent(D.data("source")),
                    url: D.attr("href") || ""
                });
                E.stopPropagation();
                y()
            })
        }
    };
    p = function (t) {
        $.ajax({
            url: i + "/s",
            data: {
                sample_name: "bear_brain",
                request_query: "百小度",
                bear_type: 0,
                request_time: +new Date(),
                request_from: 3
            },
            dataType: "jsonp",
            success: function (w) {
                var x, u;
                if (w.status == 0 && (x = w.result_content ? $.parseJSON(w.result_content) : null) && x.tip) {
                    if (w.se_query) {
                        m = encodeURIComponent(w.se_query)
                    }
                    u = k(x.tip, x.sugg || x.sugg_arr, x.link || x.link_arr, {source: encodeURIComponent(w.source_type)});
                    if (u) {
                        r(u, t);
                        f = w
                    }
                }
            }
        })
    };
    g = function (w, t) {
        var u = o;
        if (u && u.getItem("BDBEARUSER") == bds.comm.user && u.getItem("BDBEARSTATUS") == "true" && new Date(parseInt(u.getItem("BDBEARTIME"), 10)).getDate() == new Date().getDate()) {
            w && w();
            return
        }
        $.ajax({
            url: i + "/s",
            data: {request_type: 8, sample_name: "bear_brain"},
            dataType: "jsonp",
            success: function (x) {
                if (x && x.adopted == 1) {
                    u.setItem("BDBEARUSER", bds.comm.user);
                    u.setItem("BDBEARSTATUS", "true");
                    u.setItem("BDBEARTIME", +new Date);
                    w && w()
                } else {
                    t && t()
                }
            },
            error: function () {
                t && t()
            }
        })
    };
    n = function (w, t) {
        var u = a.match(/MSIE\s*(\d+)/i);
        if (!bds.comm.user || (u && (!document.documentMode || document.documentMode <= 8 || u[1] <= 8))) {
            t && t();
            return
        }
        g(w, t)
    };
    var b = $(window);
    bds.se.bear = {
        showHomeBear: function () {
            n(function () {
                var t = $(".bd_bear_home");
                if (!t.size()) {
                    return
                }
                s = t;
                t.show().get(0).className = "bd_bear_home_show";
                t.append($('<a class="bd_bear_home_bear_head" href="http://www.baidu.com/s?wd=' + m + '&bd_bear=open&tn=baiduhome_pg" target="_blank"></a>'));
                t.find(".bd_bear_home_bear_head").click(function () {
                    window.ns_c && ns_c({bear_log: 1, from: "bg_home", query: decodeURIComponent(m)})
                });
                $(window).on("index_off", function () {
                    t && t.remove && t.remove()
                });
                if (location.href.search(/bd_bear=guide/gi) > -1) {
                    t.addClass("bd_bear_home_guide");
                    t.find("a").click(function (u) {
                        u.preventDefault()
                    });
                    r("Hi，我是百小度，点这里可以找我玩耍～", t, true)
                } else {
                    p(t)
                }
            })
        }, showResultBear: function (t) {
            n(function () {
                require.config({paths: {bear: "https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/plugins/bear_9f926169.js".replace(/\.js$/, "")}});
                require(["bear"], function (u) {
                    var w = new u();
                    w.init(bds.comm.userid || bds.comm.username || bds.comm.user || $.getCookie("BAIDUID"), bds.comm.query)
                })
            }, t)
        }
    }
})();
var bds = bds || {};
bds.se = bds.se || {};
bds.se.banner = function (a, d, b) {
    this.init = function () {
        b = b || {};
        this.$dom_panel = $(a);
        this.hintText = d;
        this.hintIcon = b.iconClass || "";
        this.downUrl = b.downUrl || "";
        this.hintCookie = b.cookie || "";
        this.showNum = (this.hintCookie && $.getCookie(this.hintCookie)) ? Number($.getCookie(this.hintCookie)) : 0;
        this.nscTab = b.nscTab || "";
        this.ishome = (bds.comm && bds.comm.ishome == 1) ? 1 : 0;
        if (a && d && this.showNum < 5 && !$(".baiduapp_banner")[0] && !$(".res_top_banner")[0]) {
            this.show()
        }
    };
    this.show = function () {
        this.render();
        this.showNum += 1;
        $.setCookie(this.hintCookie, this.showNum, {expires: 30 * 24 * 60 * 60 * 1000});
        this.$dom_panel.prepend(this.bannerHtml);
        if (this.ishome != 1) {
            this.headFloat()
        }
        this.bindEvent();
        ns_c({
            fm: "behs",
            tab: ((this.ishome == 1) ? "tj_" : "") + "baidu_" + (this.nscTab ? this.nscTab : "topbanner") + "show"
        })
    };
    this.render = function () {
        var f = [];
        f = f.concat(['<div class="res_top_banner">', '<i class="c-icon ' + (this.hintIcon ? this.hintIcon : "res_top_banner_icon") + '"></i>', "<span>" + this.hintText + "</span>", (this.downUrl) ? '<a href="' + this.downUrl + '" class="res_top_banner_download">立即体验</a>' : "", '<i class="c-icon res_top_banner_close"></i>', "</div>"]);
        this.bannerHtml = f.join("")
    };
    this.headFloat = function () {
        var g = $("#head"), j = $(window), f = $(".res_top_banner");
        var i = g.css("position");
        $(window).scroll(function () {
            var l = f.height() || 0, k = j.scrollTop();
            if (k <= l) {
                g.attr("style", "position:absolute;")
            } else {
                g.attr("style", "top:0px;_top:" + l + "px;")
            }
        })
    };
    this.bindEvent = function () {
        var f = $(".res_top_banner"), g = this;
        $(".res_top_banner_download", f).on("mousedown", function () {
            g.hintCookie && $.setCookie(g.hintCookie, 5, {expires: 30 * 24 * 60 * 60 * 1000});
            ns_c({
                fm: "behs",
                tab: ((g.ishome == 1) ? "tj_" : "") + "baidu_" + (g.nscTab ? g.nscTab : "topbanner") + "down"
            })
        });
        $(".res_top_banner_close", f).on("mousedown", function () {
            f.detach();
            g.hintCookie && $.setCookie(g.hintCookie, 5, {expires: 30 * 24 * 60 * 60 * 1000});
            ns_c({
                fm: "behs",
                tab: ((g.ishome == 1) ? "tj_" : "") + "baidu_" + (g.nscTab ? g.nscTab : "topbanner") + "close"
            })
        });
        $(window).on("swap_begin", function () {
            f.detach()
        })
    };
    this.init()
};
(function () {
    $(window).on("swap_end", function () {
        var a = ["union", "union2baidu", "union_cpro", "union_nosearch", "redbull", "hao123"], g = ["union", "union2baidu", "union_cpro", "union_nosearch", "redbull"], f = bds.comm.upn, b = navigator.userAgent.toLowerCase().search(/msie [6-8]/);
        if (bds.comm.tng && $.inArray(bds.comm.tng, a) == -1) {
            if (f && f.browser && f.browser == "msie" && f.ie && (f.ie == "6" || f.ie == "7" || f.ie == "8") && b > 0) {
                var d = (f.ie == "6") ? "您的浏览器采用的IE6内核已停止维护，推荐升级到更快更安全的百度浏览器！" : "您的IE浏览器版本较低，即将停止更新维护，建议升级到更快、更安全的百度浏览器。";
                bds.se.banner($("body")[0], d, {
                    downUrl: "http://j.br.baidu.com/v1/t/ui/p/browser/tn/10105001/ch_dl_url",
                    cookie: "H_PS_BBANNER",
                    nscTab: "browser"
                })
            }
        } else {
            if (bds.comm.tng && $.inArray(bds.comm.tng, g) != -1 && bds.comm.tnuka != 1) {
                if (f && f.browser && f.os == "windows" && f.browser != "baiduclient" && f.browser != "bidubrowser") {
                    bds.se.banner($("body")[0], "添加百度到桌面，感受最便捷的搜索！", {
                        iconClass: "res_top_banner_icon_baiduapp",
                        downUrl: "http://j.br.baidu.com/v1/t/full/p/mini/tn/10000008/ch_dl_url",
                        cookie: "H_PS_BABANNER",
                        nscTab: "baiduapp"
                    })
                }
            }
        }
    })
})();
var bds = bds || {};
bds.se = bds.se || {};
bds.se.pager = bds.se.pager || {};
bds.se.pager = function () {
    this.init = function () {
        if (bds.comm.pagers && bds.comm.pagers.length > 2) {
            var a = '<div class="c-tip-menu-b"><ul>';
            $.each(bds.comm.pagers, function (b, d) {
                a += '<li><a href="/s?' + bds.comm.wfRequestData + "&pn=" + bds.comm.rn * (d - 1) + '">第' + d + "页</a></li>"
            });
            a += "</ul></div>";
            new bds.se.tip({target: $("#page .number"), align: "auto", arrow: {has: 0, offset: 25}, content: a})
        }
    };
    this.init()
};
var bds = bds || {};
bds.se = bds.se || {};
bds.se.wf = bds.se.wf || {};
bds.se.wf = function () {
    this.init = function () {
        var a = this;
        if (bds.comm.wf) {
            bds.comm.waterfallStatus = 0;
            $(window).scroll(function () {
                a.render(this)
            })
        }
    };
    this.render = function (d) {
        var b = $(d).scrollTop();
        var a = $(document).height();
        var g = $(d).height();
        if ($("#page").css("display") != "none" && $("#page .loading").length) {
            if ($(window).height() + $(window).scrollTop() > $("#page").offset().top) {
                if (!bds.comm.waterfallStatus) {
                    bds.comm.waterfallStatus = 1;
                    var f = $.ajax({
                        dataType: "parts",
                        url: "/s",
                        data: bds.comm.wfRequestData + "&pn=" + (10 * (bds.comm.pageNo * 2 - 1)) + "&wtrf=1",
                        delimiter: "</*3*/>"
                    });
                    f.parts(function (j, i, k) {
                        if (i == 0) {
                            $("#content_left").append($("#content_left", $(j)).html());
                            $(".rs", $(j)).insertBefore($("#page"));
                            $("#page").html($("#page", $(j)).html());
                            bds.se.pager();
                            bds.se.trust.init()
                        }
                    })
                }
            }
        }
    };
    this.init()
};
bds.se.safeTip = (function () {
    var selfCSS = [".unsafe_content{margin-top:4px;}", "a.unsafe_ico_new{color:#b95b07;}", ".safe_icons{width:60px;line-height:15px;font-zise:12px;color:#666;text-align:center;display:inline-block;vertical-align:top}", ".safe_icons_bd{width:16px;height:16px;display:inline-block;background:url(//www.baidu.com/cache/spam/img/safe-icons-1.1.png) no-repeat -17px 0;position:relative;z-index:0}", ".safe_icons_qq{width:16px;height:16px;display:inline-block;background:url(//www.baidu.com/cache/spam/img/safe-icons-1.1.png) no-repeat -173px 0;position:relative;z-index:0}", ".safe_icons_hs{width:16px;height:16px;display:inline-block;background:url(//www.baidu.com/cache/spam/img/safe-icons-1.1.png) no-repeat -68px 0;position:relative;z-index:0}", ".safe_icons_js{width:16px;height:16px;display:inline-block;background:url(//www.baidu.com/cache/spam/img/safe-icons-1.1.png) no-repeat 0 0;position:relative;z-index:0}", ".safe_icons_sc{width:16px;height:16px;display:inline-block;background:url(//www.baidu.com/cache/spam/img/safe-icons-1.1.png) no-repeat -51px 0;position:relative;z-index:0}", ".safe_icons_fail{width:14px;height:14px;display:inline-block;background:url(//www.baidu.com/cache/spam/img/safe-icons-1.1.png)  no-repeat -159px 0;position:absolute;left:9px;top:7px}"];

    function init() {
        bds.util.addStyle(selfCSS.join(""));
        var num_unsafe = 0, tj_which = [], data_tpl = "", data_id = [];
        var unsafe = $(".unsafe_ico_new").get();
        for (var i = 0; i < unsafe.length; i++) {
            var obj = unsafe[i];
            var s_data = eval("(" + obj.getAttribute("data-safe") + ")");
            data_id.push(obj.getAttribute("data-id"));
            data_tpl = obj.getAttribute("data-tpl");
            var s_type = {qq: "0", bd: "0", sc: "0", js: "0", hs: "0"};
            if (s_data) {
                s_item = s_data.hintItem;
                for (var j = 0; j < s_item.length; j++) {
                    s_type[s_item[j]] = "1"
                }
                for (var key in s_type) {
                    if (s_type[key] == "1") {
                        tj_which.push(key)
                    }
                }
            }
            while (obj.className.indexOf("result") == -1) {
                obj = obj.parentNode
            }
            if (obj.className.indexOf("result") != -1) {
                var h3 = obj.getElementsByTagName("h3");
                if (h3) {
                    var links = h3[0].getElementsByTagName("a");
                    if (links) {
                        var html = links[0].innerHTML;
                        links[0].href = unsafe[i].href;
                        links[0].innerHTML = html
                    }
                }
            }
            num_unsafe++;
            var safedata = eval("(" + unsafe[i].getAttribute("data-safe") + ")");
            var tip_id = unsafe[i].getAttribute("data-id");
            var tip_tpl = unsafe[i].getAttribute("data-tpl");
            setTipCon(unsafe[i], safedata, tip_id, tip_tpl)
        }
        if (num_unsafe > 0) {
            ns_c({tab: "safetip", num_unsafe: num_unsafe, prd: tj_which.join("|"), hintId: data_id, hintTpl: data_tpl})
        }
    }

    function setTipCon(obj, data, tip_id, tip_tpl) {
        var safeName = {qq: "电脑<br>管家", bd: "百度安<br>全检测", sc: "SCANV<br>安全中心", js: "金山<br>云安全", hs: "小红伞"};
        var groupName = {qq: 0, js: 0, bd: 0, sc: 0, hs: 0}, nameHtml = [], typeHtml = [], html = "";
        if (data == "") {
            return
        }
        var group = data.hintItem;
        for (var i = 0; i < group.length; i++) {
            groupName[group[i]] = 1
        }
        for (var key in groupName) {
            if (groupName[key] == 1) {
                nameHtml.push('<span class="safe_icons"><span class="safe_icons_' + key + '"><span class="safe_icons_fail"></span></span><br>' + safeName[key] + "</span>")
            }
        }
        html = '<div class="c-tip-info"><strong><em>' + data.hintLabelMid + "</em>&nbsp;&nbsp;" + data.hintLabelSuf + "</strong>";
        html += '<p class="c-gap-top-small c-gap-bottom-small">' + nameHtml.join("") + "</p></div>";
        new bds.se.tip({
            target: obj, title: data.hintLabelPre + "：", content: html, offset: {x: -20, y: 25}, onShow: function () {
                ns_c({tab: "safetip", safe: 0, which: data.hintItem.join("|"), hintId: tip_id, hintTpl: tip_tpl})
            }
        })
    }

    return {init: init}
})();
var bds = bds || {};
bds.se = bds.se || {};
bds.se.trust = bds.se.trust || {};
bds.se.trust = function () {
    var o = 4;
    var p = [];
    var n = [];
    if (bds.util && bds.util.domain && bds.util.domain.get) {
        var d = bds.util.domain.get("http://tag.baidu.com")
    } else {
        var d = "http://tag.baidu.com"
    }
    var j = null;
    var m = null;

    function q() {
        p = [], n = [];
        $(".c-trust").each(function () {
            var s = $(this);
            var r = this.getAttribute("data_key");
            if (s.parent(".c-icons-inner").length == 0) {
                s.wrap("<span class='c-icons-outer'><span class='c-icons-inner'></span></span>")
            }
            if ($.inArray(r, p) == -1) {
                p.push(this.getAttribute("data_key"))
            }
            if ($.inArray(this, n) == -1) {
                n.push(this)
            }
        });
        $(".c-trust-as").each(function () {
            m = $.parseJSON($(this).attr("hint-data"));
            if (m && !$(this).attr("render")) {
                j = $(this);
                l(m, $(this).attr("hint-type"));
                $(this).attr("render", "render")
            }
        });
        if (p.length < 1) {
            return
        }
        k()
    }

    function k() {
        $.getJSON(d + "/?urls=" + p.join(",") + "&sid=" + bds.comm.sid + "&qid=" + bds.comm.qid + "&v=" + o + "&callback=?", b)
    }

    function b(r) {
        if (r.code != 0) {
            return
        }
        $(n).each(function () {
            var s = this.getAttribute("data_key");
            m = r.data[s];
            if (!m) {
                return
            }
            j = $(this);
            j.html("");
            if (m.vstar && m.vstar.hint && m.vstar.hint.length > 0) {
                f(m.vstar.hint[0].vlevel, m.vstar.url)
            }
            if (m.medical) {
                g()
            }
            if (m.aviation) {
                i()
            }
        })
    }

    function f(u, r) {
        var s = $("<span>", {"class": "c-vline"});
        var t = $("<a>", {"class": "c-icon c-icon-v" + u, target: "_blank", onclick: "return false", href: "#"});
        if (r) {
            t.attr({href: r, onclick: ""})
        }
        j.append(s);
        j.append(t);
        A.use("honourCard", function () {
        });
        l(m.vstar, "vstar")
    }

    function g() {
        var r = $("<span>", {"class": "c-vline"});
        var s = $("<a>", {"class": "c-icon c-icon-med", target: "_blank", onclick: "return false", href: "#"});
        j.append(r);
        j.append(s);
        l(m.medical, "medical")
    }

    function i() {
        var r = $("<span>", {"class": "c-vline"});
        var s = $("<a>", {"class": "c-icon c-icon-air", target: "_blank", onclick: "return false", href: "#"});
        j.append(r);
        j.append(s);
        l(m.aviation, "aviation")
    }

    function l(t, w) {
        var z = t.hint;
        var y = "over";
        var s = t.url;
        if (!t || !z) {
            return
        }
        if (w == "vstar") {
            var x = "<div class='c-tip-cer hitcon'><ul>"
        } else {
            var x = "<div class='c-tip-info hitcon'><ul>"
        }
        for (var u = 0;
             u < z.length; u++) {
            if (z[u] == "") {
                y = "none";
                continue
            }
            x += "<li ";
            if (z[u].icon) {
                x += "class='c-tip-item-i'><img src='" + z[u].icon + "' class='c-customicon c-gap-icon-right-small c-tip-item-icon' />"
            } else {
                x += ">"
            }
            x += a(z[u].txt);
            x += "</li>"
        }
        x += "</ul></div>";
        var r = new bds.se.tip({
            target: j,
            mode: y,
            align: "auto",
            title: t.label + "：",
            content: x,
            offset: {x: 3, y: 25}
        });
        r.onShow = function () {
            var C = 1;
            for (var B = 0; B < z.length; B++) {
                if (z[B].vlevel > C) {
                    C = z[B].vlevel
                }
            }
            ns_c({hintUrl: j.attr("data_key"), hintTpl: w, hintId: C});
            if (x.indexOf("ecard") != -1) {
                setTimeout(function () {
                    A.use("honourCard", function () {
                        var D = $(r.getDom()).find(".c-trust-ecard");
                        A.ui.honourCard(D, s, C, D.attr("value"))
                    })
                }, 0)
            }
            $("li", this.dom).each(function (D) {
                $("a", this).each(function (E) {
                    this.onmousedown = function () {
                        ns_c({hintUrl: s, hintTpl: w, title: this.innerHTML, pos: E})
                    }
                })
            })
        }
    }

    function a(s) {
        var r = s;
        r = r.replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/'/g, "&#39;").replace(/"/g, "&quot;");
        r = r.replace("[/url]", "</a>").replace(/\[url ([^\s]*)\]/, "<a href='$1' target='_blank'>");
        r = r.replace(/\[img ([^\s]*)\]/, "<img src='$1' />");
        r = r.replace(/\[ecard (-?[\d]{0,3})\]/, "<div class='c-trust-ecard' value='$1'></div>");
        return r
    }

    q();
    return {init: q, render: b}
}();
var __callback_names = {};
function isbase64(p) {
    var w;
    if (bds && bds._base64) {
        w = bds._base64
    } else {
        w = {
            domain: bds.util.domain && bds.util.domain.get ? bds.util.domain.get("http://b1.bdstatic.com/") : "http://b1.bdstatic.com/",
            b64Exp: -1,
            pdc: false,
            sep: 16
        };
        bds._base64 = w
    }
    var d = {left: "content_left", right: "container"};
    var D = w.domain;
    var o = {};
    var m = [];
    var t = {left: $.Deferred(), right: $.Deferred()};
    var M = {left: {}, right: {}};
    var u = false;
    var H = 0;
    var s = 0;
    var B = 0;
    var O = null;
    var g = 1;
    w.inline = false;
    var f = [];

    function L() {
        t = {left: $.Deferred(), right: $.Deferred()}
    }

    p.onSendlog(function () {
        var Q = [];
        if (o) {
            $.each(o, function (R, S) {
                Q.push(R + "_" + S)
            })
        }
        p.setParam("cus_cusval", Q.join("|"));
        if (b.isinline()) {
            p.setParam("cus_b64il", b.ilsum);
            if (b.ilparseTime) {
                p.setParam("cus_b64ilpt", b.ilparseTime)
            }
            if (b.ilrenderTime) {
                p.setParam("cus_b64ilrt", b.ilrenderTime)
            }
        }
    });
    function P(S, U) {
        B++;
        var S = S || [], U = U || [];
        S = $.grep(S, function (V) {
            if (M.right.hasOwnProperty(V)) {
                return false
            }
            M.right[V] = false;
            return true
        });
        U = $.grep(U, function (V) {
            if (M.left.hasOwnProperty(V)) {
                return false
            }
            M.left[V] = false;
            return true
        });
        if (w.b64Exp == 2) {
            if (U.length > 0) {
                u = true;
                k(U, "left", "reql")
            }
        }
        if (S.length > 0) {
            if (S.length > 12) {
                var T = Math.round(S.length / 2);
                var R = [], Q = [];
                $.each(S, function (V, W) {
                    V < T ? R.push(W) : Q.push(W)
                });
                k(R, "right", "reqr2");
                k(Q, "right", "reqr1")
            } else {
                k(S, "right", "reqr")
            }
        }
    }

    function C(Q) {
        var S = Q, R = 0;
        while (__callback_names.hasOwnProperty(Q) || window[Q]) {
            Q = S + "_" + R;
            R++
        }
        __callback_names[Q] = 1;
        return Q
    }

    function a(T) {
        if (typeof T == "string") {
            var R, S = 0, Q = 0;
            for (R = 0; R < T.length; R++) {
                Q = R % 20 + 1;
                S += T.charCodeAt(R) << Q
            }
            return Math.abs(S)
        }
        return 0
    }

    function k(Q, U, V) {
        var S = D + "image?imglist=" + Q.join(",");
        var R = a(Q.join(""));
        R = "cb_" + (R + "").substr(Math.max(0, R.length - 8), 8) + "_" + f.length;
        R = C(R);
        S += "&cb=" + R;
        var T = new Date() * 1;
        var W = $.ajax({
            url: S,
            cache: true,
            dataType: "jsonp",
            jsonp: false,
            timeout: 1500,
            jsonpCallback: R,
            success: function (X) {
                o[V] = new Date() * 1 - T;
                if (U == "right") {
                    q(X)
                } else {
                    if (U == "left") {
                        y(X)
                    }
                }
            }
        });
        W.always(function () {
            delete __callback_names[R]
        });
        f.push(W)
    }

    function r() {
        var R = f.concat(t.left, t.right);
        var Q = O = $.when.apply($, R);
        O.always(function () {
            var S = +(new Date());
            if (Q !== O) {
                return
            }
            if (w.b64Exp == 2) {
                N("left")
            }
            N("right")
        })
    }

    var x = function (U, T, Q, R) {
        if (!R) {
            R = document.getElementById(d[T])
        } else {
            R = $(R).find("#" + d[T])[0]
        }
        if (!R) {
            return
        }
        var W = R.getElementsByTagName("IMG");
        for (var S = 0; S < W.length; S++) {
            var V = W[S].getAttribute(Q);
            if (V) {
                if (U.hasOwnProperty(V) && U[V]) {
                    z(W[S], U[V])
                } else {
                    E(W[S])
                }
            }
        }
    };
    var N = function (Q) {
        x(M[Q], Q, "data-b64-id")
    };
    var K = false;
    var I = false;
    var n = function (R, Q) {
        if (!K) {
            x(R, "right", "data-b64il-id", Q)
        }
        if (Q) {
            K = true
        }
        I = true
    };

    function i() {
        setTimeout(function () {
            for (var Q = 0; Q < m.length; Q++) {
                var R = m[Q];
                if (!R.loaded) {
                    E(R.obj)
                }
            }
            m = []
        }, 200)
    }

    function z(Q, R) {
        try {
            Q.onerror = function () {
                E(this)
            };
            m.push({obj: Q, loaded: false});
            Q.onload = function () {
                for (var U = 0; U < m.length; U++) {
                    var T = m[U];
                    if (T.obj == this) {
                        T.loaded = true
                    }
                }
            };
            Q.src = "data:image/jpeg;base64," + R
        } catch (S) {
            E(Q)
        }
    }

    var j = 0;
    var E = function (Q) {
        if ((Q.getAttribute("data-b64-id") || Q.getAttribute("data-b64il-id")) && Q.getAttribute("data-src") != null) {
            Q.src = Q.getAttribute("data-src");
            p.setParam("cus_b64fails", ++j)
        }
    };
    var q = function (Q) {
        F(Q, "right")
    };
    var y = function (Q) {
        F(Q, "left")
    };
    var F = function (S, R) {
        for (var Q in S) {
            if (S.hasOwnProperty(Q)) {
                M[R][Q] = S[Q]
            }
        }
    };
    var l = function (Q) {
        t[Q].resolve()
    };
    var J = function () {
        M = null;
        m = null;
        t = null;
        $.each(f, function () {
            this.abort()
        })
    };
    var b = {
        loadImg: P, setDomLoad: l, end: r, isinline: function () {
            return I
        }, restart: L, destroy: J, reqT: o, inline: n
    };
    return b
}
$(function () {
    if (bds.comm.user && bds.comm.user != "") {
        setTimeout(function () {
            $.ajax({
                dataType: "script",
                cache: true,
                url: (bds.su && bds.su.urStatic ? bds.su.urStatic : "http://ss.bdimg.com") + "/static/message/js/mt_show_1.7.js",
                success: function () {
                    function a() {
                        if ($("#imsg")[0] && $("#u")[0] && $("#user")[0]) {
                            bds.se.message && bds.se.message.init && bds.se.message.init({
                                button: $("#imsg"),
                                refer: $("#u")
                            });
                            $("#user").on("mouseover", function () {
                                $("#s_mod_msg").hide()
                            })
                        }
                        if ($("#imsg1")[0] && $("#u1")[0] && $("#user1")[0]) {
                            bds.se.message && bds.se.message.init && bds.se.message.init({
                                button: $("#imsg1"),
                                refer: $("#u1")
                            });
                            $("#user1").on("mouseover", function () {
                                $("#s_mod_msg").hide()
                            })
                        }
                    }

                    function b() {
                        bds.se.message && bds.se.message.addStyle && bds.se.message.addStyle()
                    }

                    bds.comm.loginAction.push(function (d, f) {
                        if (d == 1) {
                            a();
                            b()
                        }
                    });
                    if (bds.comm.newindex) {
                        $(window).on("index_off", function () {
                            setTimeout(function () {
                                a();
                                b()
                            }, 0)
                        })
                    } else {
                        a();
                        b()
                    }
                    $(window).on("swap_end", b)
                }
            })
        }, 0)
    }
});
$(window).on("swap_end", function () {
    var f = '<div id="_FP_userDataDiv" style="behavior:url(#default#userdata);width:0px;height:0px;position:absolute;top:-1000px;left:-1000px"></div><div id="_FP_comDiv" style="behavior:url(#default#clientCaps);width:0px;height:0px;position:absolute;top:-1000px;left:-1000px"></div>';
    var p = "//www.baidu.com/cache/fpid/o_0108.swf";
    var l = "//www.baidu.com/cache/fpid/ielib_0108.js";
    var d = "//www.baidu.com/cache/fpid/chromelib_0108.js";
    var j = document.title;
    var i = {flashDomId: "_FP_userDataDiv", flashUrl: p, comDomId: "_FP_comDiv", IEStoreDomId: "_FP_userDataDiv"};
    var b = navigator.userAgent.toLowerCase();
    var g = false;
    if (b.indexOf("msie") >= 0 || new RegExp("trident(.*)rv.(\\d+)\\.(\\d+)").test(b)) {
        g = true
    }
    var a = false;
    var n;
    var k = new RegExp("chrome/(\\d+)");
    var m = b.match(k);
    if (!!m) {
        a = true;
        n = m[1]
    }
    if (a && n >= 39) {
        return
    }
    $("body").append(f);
    var o = function (s) {
        if (g) {
            window.setTimeout(function () {
                document.title = j
            }, 0)
        }
        window._FPID_CACHE = s;
        $("#_FP_userDataDiv").remove();
        $("#_FP_comDiv").remove();
        var y = bds.comm.qid;
        var x = "_WWW_BR_API_" + (new Date()).getTime();
        var r = window[x] = new Image();
        r.onload = function () {
            window[x] = null
        };
        var q = $.getCookie("BAIDUID");
        var u = $.getCookie("BIDUPSID");
        var w = bds && bds.util && bds.util.domain ? bds.util.domain.get("http://eclick.baidu.com/ps_fp.htm?") : "http://eclick.baidu.com/ps_fp.htm?";
        var t = w + "pid=ps&fp=" + s.data.fp + "&im=" + s.data.im + "&wf=" + s.data.wf + "&br=" + s.data.br + "&qid=" + y + "&bi=" + q + "&psid=" + u;
        r.src = t
    };
    if (window._FPID_CACHE) {
        window._FPIDTimer = window.setTimeout(function () {
            o(window._FPID_CACHE)
        }, 2500);
        return
    }
    window._FPIDTimer = window.setTimeout(function () {
        var q = "";
        if (g) {
            q = l
        } else {
            q = d
        }
        $.ajax({
            url: q, cache: true, dataType: "script", success: function () {
                fpLib.getFp(o, i)
            }
        })
    }, 2500)
});
$(window).on("swap_begin", function () {
    if (window._FPIDTimer) {
        window.clearTimeout(window._FPIDTimer);
        $("#_FP_userDataDiv").remove();
        $("#_FP_comDiv").remove()
    }
});
var bds = bds || {};
bds.se = bds.se || {};
bds.se.upn = {
    regexp: /BD_UPN=([\w|\d]*)/, cookieset: [], write: function (a) {
        document.cookie = "BD_UPN=" + a + "; expires=" + (new Date(new Date().getTime() + 864000000)).toGMTString()
    }, set: function (a) {
        var b = this;
        try {
            if ($.isArray(a)) {
                b.cookieset = b.cookieset.concat(a)
            }
        } catch (d) {
        }
    }, run: function () {
        var f = this;
        try {
            var g = "";
            for (var d = 0; d < f.cookieset.length; d++) {
                if (f.cookieset[d] && f.cookieset[d].k && f.cookieset[d].v) {
                    var b = f.cookieset[d].k + "";
                    var a = f.cookieset[d].v + "";
                    if (b.length == a.length == 1) {
                        var l = {};
                        l[b] = a;
                        g = g + b + a
                    }
                }
            }
            f.write(g)
        } catch (j) {
        }
    }
};
bds.se.upn.set((function () {
    var a = navigator.userAgent;
    var g = a.toLowerCase();

    function m() {
        if (g.indexOf("lbbrowser") > 0) {
            return g.match(/lbbrowser/gi)
        }
        if (g.indexOf("maxthon") > 0) {
            return g.match(/maxthon\/[\d.]+/gi)
        }
        if (g.indexOf("bidubrowser") > 0) {
            return g.match(/bidubrowser/gi)
        }
        if (g.indexOf("baiduclient") > 0) {
            return g.match(/baiduclient/gi)
        }
        if (g.indexOf("metasr") > 0) {
            return g.match(/metasr/gi)
        }
        if (g.indexOf("qqbrowser") > 0) {
            return g.match(/qqbrowser/gi)
        }
        if (!(function () {
                if (navigator.mimeTypes.length > 0) {
                    var b;
                    for (b in navigator.mimeTypes) {
                        if (navigator.mimeTypes[b]["type"] == "application/vnd.chromium.remoting-viewer") {
                            return true
                        }
                    }
                }
                return false
            })() && (("track" in document.createElement("track")) && !("scoped" in document.createElement("style")) && !("v8Locale" in window) && /Gecko\)\s+Chrome/.test(navigator.appVersion)) && (("track" in document.createElement("track")) && ("scoped" in document.createElement("style")) && ("v8Locale" in window))) {
            return "qihu"
        }
        if (g.indexOf("msie") > 0) {
            return g.match(/msie [\d.]+;/gi)
        }
        if (window.document.documentMode) {
            return "msie"
        }
        if (g.indexOf("firefox") > 0) {
            return g.match(/firefox\/[\d.]+/gi)
        }
        if (g.indexOf("opr") > 0) {
            return g.match(/opr\/[\d.]+/gi)
        }
        if (g.indexOf("chrome") > 0) {
            return g.match(/chrome\/[\d.]+/gi)
        }
        if (g.indexOf("safari") > 0 && g.indexOf("chrome") < 0) {
            return g.match(/safari\/[\d.]+/gi)
        }
        return ""
    }

    browser = (m() + "").replace(/[0-9.\/|;|\s]/ig, "");
    browserversion = (function () {
        if (browser == "msie") {
            if (a.search(/MSIE [2-5]/) > 0) {
                return "ie5"
            }
            if (a.indexOf("MSIE 6") > 0) {
                return "ie6"
            }
            if (a.indexOf("MSIE 7") > 0) {
                return "ie7"
            }
            if (a.indexOf("MSIE 8") > 0) {
                return "ie8"
            }
            if (a.indexOf("MSIE 9") > 0) {
                return "ie9"
            }
            if (a.indexOf("MSIE 10") > 0) {
                return "ie10"
            }
            if (window.document.documentMode == "11") {
                return "ie11"
            }
            return "other"
        } else {
            return ""
        }
    })();
    browsertype = (function () {
        if (g.indexOf("msie") > 0 || new RegExp("trident(.*)rv.(\\d+)\\.(\\d+)").test(g)) {
            return "ie"
        }
        if (g.indexOf("firefox") > 0) {
            return "firefox"
        }
        if (g.indexOf("chrome") > 0) {
            return "chrome"
        }
        if (g.indexOf("safari") > 0 && g.indexOf("chrome") < 0) {
            return "safari"
        }
        return "other"
    })();
    function l() {
        var n = (navigator.platform == "Win32") || (navigator.platform == "Windows");
        var o = (navigator.platform == "Mac68K") || (navigator.platform == "MacPPC") || (navigator.platform == "Macintosh") || (navigator.platform == "MacIntel");
        if (o) {
            return "mac"
        }
        var b = (navigator.platform == "X11") && !n && !o;
        if (b) {
            return "unix"
        }
        var p = (String(navigator.platform).indexOf("Linux") > -1);
        if (p) {
            return "linux"
        }
        if (n) {
            return "windows"
        }
        return "other"
    }

    os = l();
    osversion = (function () {
        if (os == "windows") {
            if (a.indexOf("Windows NT 5.1") > -1 || a.indexOf("Windows XP") > -1) {
                return "xp"
            }
            if (isWinVista = a.indexOf("Windows NT 6.0") > -1 || a.indexOf("Windows Vista") > -1) {
                return "vista"
            }
            if (a.indexOf("Windows NT 6.1") > -1 || a.indexOf("Windows 7") > -1) {
                return "win7"
            }
            if (a.indexOf("Windows NT 6.2") > -1 || a.indexOf("Windows 8") > -1) {
                return "win8"
            }
            if (a.indexOf("Windows NT 6.3") > -1 || a.indexOf("Windows 8.1") > -1) {
                return "win8.1"
            }
            return "other"
        }
    })();
    var i = (function (n) {
        var b = 0;
        switch (n) {
            case"msie":
                b = 1;
                break;
            case"chrome":
                b = 2;
                break;
            case"firefox":
                b = 3;
                break;
            case"safari":
                b = 4;
                break;
            case"opr":
                b = 5;
                break;
            case"lbbrowser":
                b = 6;
                break;
            case"maxthon":
                b = 7;
                break;
            case"bidubrowser":
                b = 8;
                break;
            case"metasr":
                b = 9;
                break;
            case"qqbrowser":
                b = "a";
                break;
            case"qihu":
                b = "b";
                break;
            case"baiduclient":
                b = "c";
                break
        }
        return b
    })(browser);
    var j = (function (n) {
        var b = 0;
        switch (n) {
            case"ie6":
                b = 1;
                break;
            case"ie7":
                b = 2;
                break;
            case"ie8":
                b = 3;
                break;
            case"ie9":
                b = 4;
                break;
            case"ie10":
                b = 5;
                break;
            case"ie11":
                b = 6;
                break;
            case"other":
                b = 7;
                break;
            case"ie5":
                b = 8;
                break
        }
        return b
    })(browserversion);
    var d = (function (n) {
        var b = 0;
        switch (n) {
            case"windows":
                b = 1;
                break;
            case"mac":
                b = 2;
                break;
            case"linux":
                b = 3;
                break;
            case"unix":
                b = 4;
                break
        }
        return b
    })(os);
    var f = (function (n) {
        var b = 0;
        switch (n) {
            case"xp":
                b = 1;
                break;
            case"vista":
                b = 2;
                break;
            case"win7":
                b = 3;
                break;
            case"win8":
                b = 4;
                break;
            case"win8.1":
                b = 5;
                break;
            case"other":
                b = 6;
                break
        }
        return b
    })(osversion);
    var k = (function (n) {
        var b = 0;
        switch (n) {
            case"ie":
                b = 1;
                break;
            case"firefox":
                b = 2;
                break;
            case"chrome":
                b = 3;
                break;
            case"safari":
                b = 4;
                break
        }
        return b
    })(browsertype);
    return [{k: 1, v: i}, {k: 2, v: j}, {k: 3, v: d}, {k: 4, v: f}, {k: 5, v: k}]
})());
bds.se.upn.run();
bds.se.heightControl = {
    check: function () {
        return $("#content_right").height() > $("#content_left").height()
    }, cleanEC: function () {
        var d = $(".ec_bdtg"), b = $("#ec_im_container").children("div"), g = b.length, f = g - 1;
        if (bds.se.heightControl.check()) {
            if (d && d.length) {
                d.css("display", "none")
            }
        }
        while (bds.se.heightControl.check() && f >= 0) {
            var a = b[f];
            $(a).css("display", "none");
            f--
        }
    }, cleanRes: function () {
        var g = $("#content_right").find(".result-op"), b = g.length, a = b - 1;
        if (a == 0) {
            var f = $(g[0]).parent();
            if (($("#content_right").height() + g.height()) < $("#content_left").height()) {
                f.css({position: "static"})
            }
        } else {
            while (bds.se.heightControl.check() && a > 0) {
                var d = g[a];
                $(d).css("display", "none");
                a--
            }
        }
    }, init: function () {
        bds.se.heightControl.cleanEC();
        bds.se.heightControl.cleanRes()
    }
};
(function () {
    function a() {
        this.start = null;
        this.mouse = [];
        this.mouseTime = null;
        this.mouseSpeed = 500;
        this.key = [];
        this.scroll = [];
        this.scrollTime = null;
        this.scrollSpeed = 500;
        this.debug = false;
        this.dataStore = {};
        this.t = null;
        this.cycle = null;
        this.MIN_SPEED = 2 * 1000;
        this.MAX_SPEED = 10 * 1000;
        this.curSpeed = 5 * 1000;
        this.stayTime = 0;
        this.heartTime = [];
        this.heartT = null;
        this.MAX_LEN = 2000;
        this.storeLen = -1;
        this.MAX_SEND = 100;
        this.hostEnum = {SCLICK: 0, NSCLICK: 1};
        this.keyMap = {new_input: 0, new_disp: 0, new_view: 0, new_user: 0, new_heart: 0};
        this.hostAddr = [bds && bds.comm && bds.comm.ubsurl ? bds.comm.ubsurl + "?" : "", (bds && bds.util && bds.util.domain ? bds.util.domain.get("http://nsclick.baidu.com") : "http://nsclick.baidu.com") + "/v.gif?"];
        this.commLog = {};
        this.isFirst = true;
        this.sendNum = {};
        this.init()
    }

    a.prototype = {
        setCommLog: function (g, f, b) {
            if (!bds || !bds.comm) {
                return false
            }
            if (!(g in this.commLog)) {
                var d = {};
                if (f && b) {
                    d.log = f;
                    d.len = b
                } else {
                    d.log = "&q=" + bds.comm.queryEnc + "&qid=" + bds.comm.qid + "&rsv_tn=" + bds.comm.tn + "&rsv_sid=" + bds.comm.sid;
                    d.len = (d.log + "&t=" + new Date().getTime()).length
                }
                this.commLog[g] = d
            }
            return true
        }, fb: function () {
            var b = this.heartTime.length;
            var d;
            if (b === 0 || b === 1) {
                d = 3 * 1000
            } else {
                d = this.heartTime[b - 1] + this.heartTime[b - 2]
            }
            this.heartTime.push(d);
            return d
        }, sendHeart: function (b) {
            var f = b === 0 ? this.stayTime : new Date().getTime() - this.start;
            var g = bds && bds.comm && bds.comm.qid;
            if (g && g in this.commLog && g in this.sendNum) {
                var d = [{stay_time: f, send_num: this.sendNum[g]}];
                this.send({type: b, fm: "new_heart", data: d})
            } else {
                return
            }
        }, startHeart: function () {
            var d = this;
            var b = d.fb();
            d.stayTime += b;
            d.heartT = setTimeout(function () {
                d.sendHeart(0);
                d.startHeart()
            }, b)
        }, preInit: function () {
            this.start = new Date().getTime();
            this.mouse = [];
            if (this.mouseTime !== null) {
                clearTimeout(this.mouseTime)
            }
            this.mouseTime = null;
            this.key = [];
            this.scroll = [];
            if (this.scrollTime !== null) {
                clearTimeout(this.scrollTime)
            }
            this.scrollTime = null;
            this.cycle = null;
            if (this.t !== null) {
                clearTimeout(this.t)
            }
            this.t = null;
            this.storeLen = -1;
            var b = bds && bds.comm && bds.comm.qid ? bds.comm.qid : "";
            if (b) {
                this.setCommLog(b);
                this.sendNum[b] = 0
            }
            if (bds && bds.comm && (bds.comm.logFlagSug === 1 || bds.comm.globalLogFlag === 1) && bds.comm.ishome === 0) {
                if (this.heartT !== null) {
                    clearTimeout(this.heartT)
                }
                this.heartT = null;
                this.stayTime = 0;
                this.heartTime = [];
                this.startHeart()
            }
        }, collectPoint: function (d, j) {
            var f = d + "Time";
            var i = this[d + "Speed"];
            var k = this;
            if (k[d].length === 0) {
                var b = g(d, j);
                if (b.length < 2) {
                    return
                }
                k[d].push([new Date().getTime() - k.start, b[0], b[1]]);
                return
            }
            if (k[f] === null) {
                k[f] = setTimeout(function () {
                    var l = g(d, j);
                    if (l.length < 2) {
                        k[f] = null;
                        return
                    }
                    k[d].push([new Date().getTime() - k.start, l[0], l[1]]);
                    k[f] = null
                }, i)
            }
            function g(m, n) {
                var l = [];
                if (m === "mouse") {
                    l[0] = n.pageX;
                    l[1] = n.pageY
                } else {
                    if (m === "scroll") {
                        var o = $(window);
                        l[0] = o.scrollLeft();
                        l[1] = o.scrollTop()
                    }
                }
                return l
            }
        }, singleInit: function () {
            var b = this;
            $("body").on("mousemove", function (d) {
                b.collectPoint("mouse", d)
            }).on("keydown", function (d) {
                b.key.push([new Date().getTime() - b.start, d.keyCode])
            });
            $(window).on("scroll", function (d) {
                b.collectPoint("scroll", d)
            });
            b.singleInit = function () {
            }
        }, flushData: function (b) {
            if (this.t !== null) {
                clearTimeout(this.t);
                this.t = null
            }
            this.startSend(this.fetchData(b, true), true);
            this.startSend(this.fetchData(b, true));
            if (bds && bds.comm && (bds.comm.logFlagSug === 1 || bds.comm.globalLogFlag === 1)) {
                if (this.heartT !== null) {
                    clearTimeout(this.heartT);
                    this.heartT = null
                }
                this.sendHeart(b)
            }
        }, init: function () {
            var b = this;
            b.preInit();
            $(window).on("swap_begin", function () {
                if (b.t !== null) {
                    clearTimeout(b.t);
                    b.t = null
                }
                if (bds && bds.comm && bds.comm.ishome === 0 && (bds.comm.logFlag === 1 || bds.comm.globalLogFlag === 1) && b.isFirst === false) {
                    b.sendHeart(1)
                }
            }).on("unload", function () {
                if (bds && bds.comm && bds.comm.ishome === 0 && (bds.comm.logFlagSug === 1 || bds.comm.globalLogFlag === 1)) {
                    b.flushData(2)
                }
            }).on("swap_end", function () {
                b.preInit();
                if (b.isFirst === true) {
                    b.isFirst = false
                }
                if (!b.hostAddr[0] && bds && bds.comm && bds.comm.ubsurl) {
                    b.hostAddr[0] = bds.comm.ubsurl + "?"
                }
            })
        }, getData: function (j, l, b) {
            if (this.start === null || j.length === 0) {
                return {startTime: this.start, record: []}
            }
            var d = {startTime: this.start, record: []};
            var g = l;
            var k = b;
            if (g === undefined) {
                g = 0;
                k = j[j.length - 1][0]
            } else {
                if (g !== undefined && typeof g === "number" && k === undefined) {
                    g = g - this.start;
                    k = j[j.length - 1][0]
                } else {
                    if (g !== undefined && typeof g === "number" && k !== undefined && typeof k === "number") {
                        g = g - this.start;
                        k = k - this.start
                    } else {
                        g = 0;
                        k = 0
                    }
                }
            }
            for (var f in j) {
                if (j[f][0] < g) {
                    continue
                }
                if (j[f][0] < k) {
                    d.record.push(j[f])
                }
                if (j[f][0] >= k) {
                    break
                }
            }
            return d
        }, send: function (j, g, m) {
            if (!j) {
                return false
            }
            if (this.debug) {
            }
            if (g === 0 && !this.hostAddr[0]) {
                if (bds && bds.comm && bds.comm.ubsurl) {
                    this.hostAddr[0] = bds.comm.ubsurl + "?"
                } else {
                    return false
                }
            }
            var f = "";
            var i = "";
            var l = "";
            if (typeof j === "object") {
                for (var d in j) {
                    f = j[d];
                    if (typeof f === "object") {
                        f = $.stringify(f)
                    }
                    i += d + "=" + encodeURIComponent(f) + "&"
                }
                i = i.substring(0, i.length - 1)
            } else {
                if (typeof j === "string") {
                    i = j
                }
            }
            if (!m && bds && bds.comm && bds.comm.qid) {
                m = bds.comm.qid
            }
            if (m && m in this.commLog) {
                i += this.commLog[m]["log"];
                i += "&t=" + new Date().getTime()
            } else {
                return false
            }
            if (typeof g !== "number" || g < 0 || g >= this.hostAddr.length) {
                g = 0
            }
            l = this.hostAddr[g] + i;
            if (l.length > this.MAX_LEN) {
                return false
            } else {
                var b = window["BD_PS_C" + (new Date()).getTime()] = new Image();
                b.src = this.hostAddr[g] + i
            }
            return true
        }, sendNow: function (b, g, d) {
            if (!b || typeof b !== "string" || !(b in this.keyMap) || !g) {
                return
            }
            var f = "type=3&fm=" + b + "&data=" + encodeURIComponent($.stringify([g]));
            if (d && d.qid && d.log && d.len) {
                this.setCommLog(d.qid, d.log, d);
                this.send(f, this.keyMap[b], d.qid)
            } else {
                send(f, this.keyMap[b])
            }
        }, pushData: function (b, g, d) {
            var i = bds && bds.comm && bds.comm.qid ? bds.comm.qid : "";
            if (!i) {
                return false
            }
            if (d && d.qid && d.log && d.len) {
                this.setCommLog(d.qid, d.log, d.len);
                i = d.qid
            } else {
                this.setCommLog(i)
            }
            if (!(i in this.dataStore)) {
                this.dataStore[i] = {}
            }
            var f = this.dataStore[i];
            if (!(b in f)) {
                f[b] = [[], []]
            }
            if (d && d.level === true) {
                f[b][0].push(encodeURIComponent($.stringify(g)))
            } else {
                f[b][1].push(encodeURIComponent($.stringify(g)))
            }
        }, fetchData: function (l, b) {
            var f = this.dataStore;
            var k;
            var o;
            var n = [];
            var p = 0;
            var i = false;
            var d = bds && bds.comm && bds.comm.qid ? bds.comm.qid : "";
            if (!d) {
                return []
            }
            for (var j in f) {
                if (j !== d) {
                    i = true
                } else {
                    i = false
                }
                k = f[j];
                for (var m in k) {
                    if (!(m in this.keyMap)) {
                        continue
                    }
                    p = this.keyMap[m];
                    if (typeof p !== "number" || this.hostAddr[p] === undefined) {
                        continue
                    }
                    if (k[m][0].length > 0) {
                        o = k[m][0]
                    } else {
                        o = k[m][1]
                    }
                    g.call(this);
                    if ((i === true || b !== true) && o.length === 0 && k[m][1].length > 0) {
                        o = k[m][1];
                        g.call(this)
                    }
                    if (i === true) {
                        delete this.dataStore[j]
                    }
                }
            }
            return n;
            function g() {
                var q;
                var x = 0;
                var t = [];
                var z = false;
                var u = this.commLog[j]["len"];
                var y = this.hostAddr[p].length;
                var w = y + ("type=" + l + "&fm=" + m + "&data=").length + u;
                var s = w + 6;
                var r = s;
                while (o.length !== 0 && x < this.MAX_SEND) {
                    if (i === false && l === 0) {
                        x++
                    }
                    q = o.shift();
                    t.push(q);
                    r = s + q.length + 3;
                    if (s >= this.MAX_LEN || r >= this.MAX_LEN) {
                        if (t.length >= 2) {
                            t.pop();
                            z = true
                        }
                        n.push({qid: j, key: m, type: l, data: "%5B" + t.join("%2C") + "%5D", host: p});
                        t = [];
                        if (z) {
                            t[0] = q;
                            z = false
                        }
                        if (t.length > 0) {
                            r = w + 3 + q.length + 3
                        } else {
                            r = w + 6
                        }
                    }
                    s = r
                }
                if (t.length > 0) {
                    n.push({qid: j, key: m, type: l, data: "%5B" + t.join("%2C") + "%5D", host: p})
                }
            }
        }, startSend: function (i, j) {
            var k = this;
            var b;
            var g;
            var f = j === true ? 0 : 100;
            var d = setInterval(function () {
                if (i.length <= 0) {
                    clearInterval(d);
                    return
                }
                b = i.shift();
                if (b && b.qid && b.qid in k.commLog) {
                    g = "type=" + b.type + "&fm=" + b.key + "&data=" + b.data
                } else {
                    return
                }
                k.send(g, b.host, b.qid);
                if (b.qid in k.sendNum) {
                    k.sendNum[b.qid] += 1
                }
            }, f)
        }, startCycle: function () {
            var b = this;
            if (b.cycle === null) {
                b.cycle = 1
            }
            b.t = setTimeout(function () {
                var f = b.fetchData(0);
                var d = f.length;
                if (b.storeLen === -1) {
                    b.storeLen = d
                }
                if (b.storeLen !== 0 && d / b.storeLen >= 2 && b.curSpeed > b.MIN_SPEED) {
                    b.curSpeed -= 1000
                }
                if ((d === 0 || b.storeLen / d >= 2) && b.curSpeed < b.MAX_SPEED) {
                    b.curSpeed += 1000
                }
                b.startSend(f, 0);
                b.startCycle()
            }, b.curSpeed)
        }, outInterface: function () {
            var b = this;
            return {
                hostEnum: b.hostEnum, api: {
                    getMouseLocus: function (f, d) {
                        return b.getData(b.mouse, f, d)
                    }, getKeyRecord: function (f, d) {
                        return b.getData(b.key, f, d)
                    }, getScrollRecord: function (f, d) {
                        return b.getData(b.scroll, f, d)
                    }, startAPI: function () {
                        b.singleInit()
                    }
                }, send: {
                    debug: function () {
                        b.debug = true
                    }, send: function (f, d) {
                        return b.send(f, d)
                    }, sendNow: function (d, g, f) {
                        return b.sendNow(d, g, f)
                    }, sendPack: function (d, g, f) {
                        if (!d || typeof d !== "string" || !(d in b.keyMap) || !g) {
                            return
                        }
                        b.pushData(d, g, f);
                        if (b.cycle === null) {
                            b.startCycle()
                        }
                    }
                }
            }
        }
    };
    bds.log = new a().outInterface()
})();
$(window).on("swap_end", function () {
    if (bds.comm.encTn) {
        $.setCookie("H_PS_645EC", bds.comm.encTn, {expires: 24 * 60 * 60 * 30})
    }
    if (bds.se.trust) {
        bds.se.trust.init()
    }
    bds.se.heightControl.init();
    bds.util.setContainerWidth();
    if ($(".content_none").length > 0) {
        new bds.util.setFootStyle()
    }
    $(document).delegate(".feedback", "click", function () {
        var d = this;
        $.getScript("https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/plugins/feedback_e510124a.js", function () {
            var i = d.getAttribute("data-feedbackid") || 1;
            var g = {product_id: 18, entrance_id: i};
            var f = {username: bds.comm.username, query: bds.comm.query, fb_qid: bds.comm.qid};
            bds.qa.ShortCut.initRightBar(g, f)
        })
    });
    var a = $("#form").find('input[type="hidden"][name=rsv_pq]');
    if (a.length) {
        $(a).val(bds.comm.qid)
    } else {
        $("#form").append('<input type="hidden" name="rsv_pq" value="' + bds.comm.qid + '"/>')
    }
    var b = $("#form").find('input[type="hidden"][name=rsv_t]');
    if (b.length) {
        $(b).val(bds.comm.encTn)
    } else {
        $("#form").append('<input type="hidden" name="rsv_t" value="' + bds.comm.encTn + '"/>')
    }
    bds.ready(function () {
        var d = 0;
        bds.se.pager();
        bds.se.wf();
        $(".rs").width($(window).width() - 121);
        $(window).scroll(function () {
            $(".rs").width($(window).width() - 121);
            if (!d) {
                if ($("#page").length && $("#page").css("display") != "none") {
                    if ($(window).height() + $(window).scrollTop() > $("#page").offset().top) {
                        c({fm: "inlo", rsv_wf: "1"});
                        d = 1
                    }
                }
            }
        });
        bds.event.on("se.window_resize", function () {
            $(".rs").width($(window).width() - 121)
        })
    })
});
(function () {
    $(window).one("swap_end", function () {
        $("body").on("mousedown", ".se_common_hint a", function () {
            var a = $(this), f = a.parents(".se_common_hint"), g = f.attr("data-id") || "", d = f.attr("data-tpl") || "", b = f.find("a").index(a);
            ns_c_pj({hintId: g, hintTpl: d, title: a.html(), pos: b, qid: bds.comm.qid || ""}, "pj=hint&")
        })
    })
})();
$(function () {
    $("#u,#u1").delegate(".lb", "click", function () {
        try {
            bds.se.login.open()
        } catch (a) {
        }
    })
});
$.ajax({
    dataType: "script",
    cache: true,
    url: "https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/home/js/nu_instant_search_c15b8d14.js"
});
window.PRE_CONN = (function () {
    var d = function (l, m) {
        var p = new Date() * 1;
        l = bds.util.domain && bds.util.domain.get ? bds.util.domain.get(l) : l;
        var n = /^(http[s]?:\/\/)?([^\/]+)(.*)/, o = l.match(n);
        if (o[2] && !b[o[2]]) {
            b.push(o[2]);
            var q = new Image();
            q.src = l + "?_t=" + (m ? m : p);
            q.onload = (q.onerror = function () {
                q = null
            })
        }
    }, k, g = 0, b = [], j = function () {
        try {
            if (!window.pageState || window.pageState == 0 || g == 1) {
                $("#kw1,#kw").one("keydown", function () {
                    if (location.protocol === "https:") {
                        d("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/ps_default.gif");
                        d("https://ss1.baidu.com/6ONWsjip0QIZ8tyhnq/ps_default.gif");
                        d("https://ss2.baidu.com/6ONWsjip0QIZ8tyhnq/ps_default.gif");
                        d("https://ss3.baidu.com/6ONWsjip0QIZ8tyhnq/ps_default.gif")
                    } else {
                        d("http://b1.bdstatic.com/img/pc.gif", parseInt(Math.random() * 1000));
                        d("http://ecma.bdimg.com/public03/pc.gif");
                        $.each(["i7", "i8", "i9", "t10", "t11", "t12"], function (m, n) {
                            d("http://" + n + ".baidu.com/ps_default.gif")
                        })
                    }
                });
                if (g == 1) {
                    $("#kw1,#kw").one("focus", function () {
                        if (location.protocol === "https:") {
                            d("https://www.baidu.com/con?from=self")
                        }
                    })
                }
            }
        } catch (l) {
        }
    }, a = function () {
        g = 1;
        j();
        i()
    }, f = function () {
        i();
        k = setTimeout(a, 1000 * 55)
    }, i = function () {
        clearTimeout(k);
        g = 0
    };
    j();
    return {init: j, startTimer: f}
})();
(function () {
    $.ajaxPrefilter("parts", function (b, a, d) {
        b.__partsCallback = [];
        b.__partsIndex = 0;
        d.parts = function (f) {
            b.__partsCallback.push(f)
        };
        if (b.parts) {
            d.parts(b.parts)
        }
        b.converters["* parts"] = function (f) {
            return f
        }
    });
    $.ajaxTransport("parts", function (a) {
        if (!a.crossDomain || support.cors) {
            var b;
            return {
                send: function (j, d) {
                    var f, g = a.xhr();
                    g.open(a.type, a.url, a.async, a.username, a.password);
                    if (a.xhrFields) {
                        for (f in a.xhrFields) {
                            g[f] = a.xhrFields[f]
                        }
                    }
                    if (a.mimeType && g.overrideMimeType) {
                        g.overrideMimeType(a.mimeType)
                    }
                    if (!a.crossDomain && !j["X-Requested-With"]) {
                        j["X-Requested-With"] = "XMLHttpRequest"
                    }
                    for (f in j) {
                        if (j[f] !== undefined) {
                            g.setRequestHeader(f, j[f] + "")
                        }
                    }
                    g.send((a.hasContent && a.data) || null);
                    b = function (m, l) {
                        var k, q, o, n;
                        if ((g.readyState === 3 || g.readyState === 4) && !l) {
                            (function () {
                                var r = a.delimiter;
                                var w = "";
                                try {
                                    w = g.responseText
                                } catch (x) {
                                }
                                if (w == "") {
                                    return
                                }
                                var y = -1, u, t = 0, s;
                                if (r) {
                                    while (true) {
                                        for (;
                                            t <= a.__partsIndex; t++) {
                                            u = (y == -1) ? 0 : y + r.length;
                                            y = w.indexOf(r, u);
                                            if (y == -1) {
                                                break
                                            }
                                        }
                                        if (y == -1 && g.readyState !== 4) {
                                            return
                                        }
                                        for (s = 0; s < a.__partsCallback.length; s++) {
                                            a.__partsCallback[s].call(g, w.substring(u, y == -1 ? w.length : y), a.__partsIndex, w)
                                        }
                                        a.__partsIndex++;
                                        if (y == -1) {
                                            return
                                        }
                                    }
                                } else {
                                    for (t = 0; t < a.__partsCallback.length; t++) {
                                        a.__partsCallback[t].call(g, w)
                                    }
                                }
                            })()
                        }
                        if (b && (l || g.readyState === 4)) {
                            b = undefined;
                            g.onreadystatechange = jQuery.noop;
                            if (l) {
                                if (g.readyState !== 4) {
                                    g.abort()
                                }
                            } else {
                                o = {};
                                k = g.status;
                                if (typeof g.responseText === "string") {
                                    o.text = g.responseText
                                }
                                try {
                                    q = g.statusText
                                } catch (p) {
                                    q = ""
                                }
                                if (!k && a.isLocal && !a.crossDomain) {
                                    k = o.text ? 200 : 404
                                } else {
                                    if (k === 1223) {
                                        k = 204
                                    }
                                }
                            }
                        }
                        if (o) {
                            d(k, q, o, g.getAllResponseHeaders())
                        }
                    };
                    if (!a.async) {
                        b()
                    } else {
                        if (g.readyState === 4) {
                            setTimeout(b)
                        } else {
                            g.onreadystatechange = b
                        }
                    }
                }, abort: function () {
                    if (b) {
                        b(undefined, true)
                    }
                }
            }
        }
    })
})();
(function () {
    var defaultOptions = {
        sugSet: 1,
        sugStoreSet: 1,
        isSwitch: 1,
        isJumpHttps: 1,
        imeSwitch: 0,
        resultNum: 10,
        skinOpen: 1,
        resultLang: 0
    }, options = {}, tmpName;
    var expire30y = new Date();
    expire30y.setTime(expire30y.getTime() + 30 * 365 * 86400000);
    try {
        if (bds && bds.comm && bds.comm.personalData) {
            if (typeof bds.comm.personalData == "string") {
                bds.comm.personalData = eval("(" + bds.comm.personalData + ")")
            }
            if (!bds.comm.personalData) {
                return
            }
            for (tmpName in bds.comm.personalData) {
                if (defaultOptions.hasOwnProperty(tmpName) && bds.comm.personalData.hasOwnProperty(tmpName)) {
                    if (bds.comm.personalData[tmpName].ErrMsg == "SUCCESS") {
                        options[tmpName] = bds.comm.personalData[tmpName].value
                    }
                }
            }
        }
        try {
            if (!parseInt(options.resultNum)) {
                delete (options.resultNum)
            }
            if (!parseInt(options.resultLang) && options.resultLang != "0") {
                delete (options.resultLang)
            }
        } catch (e) {
        }
        writeCookie();
        if (!("sugSet" in options)) {
            options.sugSet = (Cookie.get("sug", 3) != 3 ? 0 : 1)
        }
        if (!("sugStoreSet" in options)) {
            options.sugStoreSet = Cookie.get("sugstore", 0)
        }
        var BAIDUID = Cookie.get("BAIDUID");
        if (!("resultNum" in options)) {
            if (/NR=(\d+)/.test(BAIDUID)) {
                options.resultNum = RegExp.$1 ? parseInt(RegExp.$1) : 10
            } else {
                options.resultNum = 10
            }
        }
        if (!("resultLang" in options)) {
            if (/SL=(\d+)/.test(BAIDUID)) {
                options.resultLang = RegExp.$1 ? parseInt(RegExp.$1) : 0
            } else {
                options.resultLang = 0
            }
        }
        if (!("isSwitch" in options)) {
            options.isSwitch = (Cookie.get("ORIGIN", 0) == 2 ? 0 : (Cookie.get("ORIGIN", 0) == 1 ? 2 : 1))
        }
        if (!("imeSwitch" in options)) {
            options.imeSwitch = Cookie.get("bdime", 0)
        }
    } catch (e) {
    }
    function save(callback) {
        var optionsStr = [];
        for (tmpName in options) {
            if (options.hasOwnProperty(tmpName)) {
                optionsStr.push('"' + tmpName + '":"' + options[tmpName] + '"')
            }
        }
        var str = "{" + optionsStr.join(",") + "}";
        if (bds.comm.personalData) {
            $.ajax({
                url: "//www.baidu.com/ups/submit/addtips/?product=ps&tips=" + encodeURIComponent(str) + "&_r=" + new Date().getTime(),
                success: function () {
                    writeCookie();
                    if (typeof callback == "function") {
                        callback()
                    }
                }
            })
        } else {
            writeCookie();
            if (typeof callback == "function") {
                setTimeout(callback, 0)
            }
        }
    }

    function set(optionName, value) {
        options[optionName] = value
    }

    function get(optionName) {
        return options[optionName]
    }

    function writeCookie() {
        if (options.hasOwnProperty("sugSet")) {
            var value = options.sugSet == "0" ? "0" : "3";
            clearCookie("sug");
            Cookie.set("sug", value, document.domain, "/", expire30y)
        }
        if (options.hasOwnProperty("sugStoreSet")) {
            var value = options.sugStoreSet == 0 ? "0" : "1";
            clearCookie("sugstore");
            Cookie.set("sugstore", value, document.domain, "/", expire30y)
        }
        if (options.hasOwnProperty("isSwitch")) {
            var ORINGIN_MAP = {0: "2", 1: "0", 2: "1"};
            var value = ORINGIN_MAP[options.isSwitch];
            clearCookie("ORIGIN");
            Cookie.set("ORIGIN", value, document.domain, "/", expire30y)
        }
        if (options.hasOwnProperty("imeSwitch")) {
            var value = options.imeSwitch;
            clearCookie("bdime");
            Cookie.set("bdime", value, document.domain, "/", expire30y)
        }
    }

    function writeBAIDUID() {
        var BAIDUID = Cookie.get("BAIDUID"), NR, FG, SL;
        if (/FG=(\d+)/.test(BAIDUID)) {
            FG = RegExp.$1
        }
        if (/SL=(\d+)/.test(BAIDUID)) {
            SL = RegExp.$1
        }
        if (/NR=(\d+)/.test(BAIDUID)) {
            NR = RegExp.$1
        }
        if (options.hasOwnProperty("resultNum")) {
            NR = options.resultNum
        }
        if (options.hasOwnProperty("resultLang")) {
            SL = options.resultLang
        }
        Cookie.set("BAIDUID", BAIDUID.replace(/:.*$/, "") + (typeof SL != "undefined" ? ":SL=" + SL : "") + (typeof NR != "undefined" ? ":NR=" + NR : "") + (typeof FG != "undefined" ? ":FG=" + FG : ""), ".baidu.com", "/", expire30y, true)
    }

    function clearCookie(name) {
        Cookie.clear(name, "/");
        Cookie.clear(name, "/", document.domain);
        Cookie.clear(name, "/", "." + document.domain);
        Cookie.clear(name, "/", ".baidu.com")
    }

    function reset(callback) {
        options = defaultOptions;
        save(callback)
    }

    window.UPS = {writeBAIDUID: writeBAIDUID, reset: reset, get: get, set: set, save: save}
})();
var ie = navigator.userAgent.toLowerCase().match(/msie\s+(\d*)/);
var ie6 = ie && ie[1] == 6;
if (window._is_skin_sam && !ie6) {
    var url = "";
    if (window._is_skin_sam == "1") {
        url = "https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/skin/js/skin_1_47132044.js"
    } else {
        if (window._is_skin_sam == "2") {
            url = "https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/skin/js/skin_2_29b21d24.js"
        } else {
            if (window._is_skin_sam == "3") {
                url = "https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/skin/js/skin_3_ddcfc386.js"
            }
        }
    }
    var skinDefer = null;
    if (url) {
        var skinDefer = $.ajax({dataType: "script", cache: true, url: url})
    }
    skinDefer && skinDefer.done(function () {
        $(window).on("swap_end", function () {
            bds.se.skin && new bds.se.skin()
        });
        $(window).on("swap_begin", function () {
            bds.se.skin && bds.se.skin.prototype.dispose()
        })
    })
}
(function () {
    var i = {};
    var d = function (k) {
        var j = f(k);
        a(j)
    }, b = function (j, l) {
        var m = Math.random();
        if (m > 0.2 && m < 0.201 && location.protocol == "http:") {
            i.url = j;
            i.headers = l;
            var k = $.ajax({url: j, headers: l, success: d})
        }
    }, f = function (j) {
        if (typeof j === "string" && typeof i.headers === "object") {
            if (i.headers.hasOwnProperty("content_syni") && j.length !== 12495) {
                return j
            }
            if (i.headers.hasOwnProperty("content_syns") && j.length !== 19295) {
                return j
            }
        }
        return "normal"
    }, a = function (j) {
        $.ajax({url: "//www.baidu.com/r/plog", type: "post", data: {page_html: j}})
    };
    var g = window.ctwin = {sendRequest: b}
})();
var bds = bds || {};
bds.se = bds.se || {};
bds.se.speedTester = (function () {
    function a() {
    }

    function b(j, i, g) {
        g = g || 19558;
        f(j, (function (k) {
            return function (m, n, l) {
                d(k, n, l)
            }
        })(i), (function (k) {
            return function (l) {
                d(k)
            }
        })(i), g)
    }

    function f(k, l, m, g) {
        l = l || a;
        m = m || a;
        var i = new Image();
        i.onload = function () {
            this.onload = this.onerror = null;
            g = this.fileSize || g;
            var o = new Date(), p = o - j, n = g / p;
            l(this, p, n)
        };
        i.onerror = function () {
            this.onload = this.onerror = null;
            m(this)
        };
        var j = new Date();
        i.src = k
    }

    function d(j, k, i) {
        var g = new Image();
        g.onload = g.onerror = function () {
            this.onload = this.onerror = null
        };
        g.src = (j + (k ? ("&t=" + k + "&v=" + i) : "&t=-1&v=-1") + "&r=" + Math.random())
    }

    return {start: b}
})();
bds.se.speedMonitor = function (k) {
    var b = k.logPath || "", l = k.flag || "default", j = k.sleep || "1000", i = false, n = null;
    var g = [];
    var a = b + "?flag=" + l;

    function f() {
        var o = g.pop();
        if (o) {
            d(o)
        }
        if (i) {
            n = window.setTimeout(f, j)
        }
    }

    function d(o) {
        var r = o.url, p = o.size || -1, q = [];
        q.push("id=" + encodeURIComponent(o.id));
        q.push("name=" + encodeURIComponent(o.name));
        q.push("url=" + encodeURIComponent(o.url));
        q.push("size=" + encodeURIComponent(o.id));
        for (key in o.logData) {
            q.push(key + "=" + encodeURIComponent(o.logData[key]))
        }
        bds.se.speedTester.start(r, a + "&" + q.join("&"), p)
    }

    function m() {
        return true
    }

    this.start = function () {
        this.stop();
        i = true;
        f()
    };
    this.stop = function () {
        i = false;
        window.clearInterval(n)
    };
    this.addTask = function (o) {
        if (m(o)) {
            g.push(o)
        }
    };
    this.clear = function () {
        g = []
    }
};
setTimeout(function () {
    var b = Math.random();
    if (b < 0.001 && location.protocol == "http:") {
        var a = document.createElement("script");
        a.src = "http://velocity.baidu.com/sp.php";
        document.body.appendChild(a)
    }
}, 1000);
(function (a) {
    var a = a || {};
    a.se = a.se || {};
    a.se.QuickDelete = function (f, d) {
        this.form = f;
        this.options = d;
        this._init()
    };
    a.se.QuickDelete.prototype = {
        constructor: a.se.QuickDelete, _init: function () {
            this._create_elem();
            this._bind_event()
        }, _create_elem: function () {
            var g = this.form, i = this.options, m = i.top || 0, j = i.right || 0, f = $.trim(g.val()) ? "block" : "none", l = "quickdelete", k = g.parent(), d = $('<a href="javascript:;"></a>').attr("id", l).attr("title", "清空").addClass("quickdelete");
            k.addClass("quickdelete-wrap").append(d);
            d.css({top: m + "px", right: j + "px", display: f});
            i.wrapElem = k;
            i.elem = d
        }, _show: function () {
            if (a.comm.ishome === 0) {
                this.options.elem.show()
            }
        }, _hide: function () {
            this.options.elem.hide()
        }, _bind_event: function () {
            var f = this.form, d = this.options.elem, g = this;
            f.on("focus", function () {
                $.trim(f.val()) ? g._show() : g._hide()
            }).on("keyup input propertychange", function () {
                $.trim(f.val()) ? g._show() : g._hide()
            });
            d.on("click", function () {
                var i = a.comm.supportis ? 2 : 0;
                ns_c({input_clear: a.comm.ishome + i, delete_query: encodeURIComponent(f.val())});
                f.val("").focus();
                g._hide();
                return false
            });
            $(window).on("swap_end index_off", function () {
                $.trim(f.val()) ? g._show() : g._hide()
            })
        }
    };
    var b = new a.se.QuickDelete($("#kw"), {top: 0, right: 0})
})(bds);
if (window.bds && bds.comm && bds.comm.ishome) {
    $(window).on("load", function () {
        if (window.ctwin) {
            window.ctwin.sendRequest("//www.baidu.com/?tn=baidu", {content_syni: 1})
        }
        if (window.performance && performance.timing) {
            var f = function () {
                var i = g("navigation"), n = g("domainLookup"), q = g("connect"), j = g("secureConnection"), o = g("redirect"), m = g("request"), k = g("response"), l = {
                    start: performance.timing.domLoading,
                    end: performance.timing.domComplete
                }, p = g("loadEvent");
                return {
                    navigation: q.start - i.start,
                    dns: n.value,
                    tcp: q.value,
                    ssl: j.start > 0 ? q.end - j.start : 0,
                    request: k.start - m.start,
                    response: k.value,
                    dom: l.end - l.start,
                    loadEvent: p.end - i.start
                }
            };
            var a = Cookie.get("__bsi");
            var g = function (i) {
                var k = performance.timing, l = k[i + "Start"] ? k[i + "Start"] : 0, j = k[i + "End"] ? k[i + "End"] : 0;
                return {start: l, end: j, value: j - l > 0 ? j - l : 0}
            };
            var b = function () {
                var o = [], n = f();
                for (var l in n) {
                    o.push(l + "=" + n[l])
                }
                o.push("protocol=" + encodeURIComponent(location.protocol));
                var p = "//www.baidu.com/nocache/fesplg/s.gif?log_type=hm&type=timing&", q = "";
                q += o.join("&");
                q += "&newindex=" + (window.bds && bds.comm ? bds.comm.newindex : -1);
                if (a) {
                    q += "&bsi=" + a
                }
                var k = p + q, j = new Image(), m = "_LOG_" + new Date().getTime();
                j.onload = function () {
                    delete window[m]
                };
                window[m] = j;
                j.src = k
            };
            var d = Math.random();
            if (/8498/.test(bds.comm.indexSid) && d < 0.01) {
                setTimeout(b, 500)
            }
        }
    })
}
(function () {
    bds.se.skeleton = function () {
        var b;
        return function () {
            if (!b) {
                b = a();
                $(window).one("swap_begin", function () {
                    b = null
                })
            }
            return b
        }
    }();
    function a() {
        var d = {}, b = {};
        var g = $("#wrapper");
        d.topResult = g.find("#con-at").find(".result-op");
        d.rightResult = g.find("#con-ar").find(".result-op");
        d.leftResult = g.find("#content_left").find(".result, .result-op");
        if (d.topResult.length) {
            b.T = [];
            d.topResult.each(function () {
                b.T.push(f("T", $(this)))
            })
        }
        if (d.rightResult.length) {
            b.R = [];
            d.rightResult.each(function () {
                b.R.push(f("R", $(this)))
            })
        }
        if (d.leftResult.length) {
            b.L = [];
            d.leftResult.each(function () {
                b.L.push(f("L", $(this)))
            })
        }
        return b;
        function f(j, i) {
            var n = {top: i.offset().top, left: i.offset().left};
            var k = {width: i.width(), height: i.height()};
            var l = function () {
                    var p = i.attr("data-click");
                    if (p) {
                        try {
                            return $.parseJSON(p)
                        } catch (o) {
                        }
                    }
                }() || {};
            var m = j + (l.p5 || "");
            return {id: m, pos: n, size: k, dataClick: l, dom: i}
        }
    }
})();
(function () {
    bds.se.display = function () {
        var b = new a()
    };
    function a() {
        var b = this;
        b.display = {};
        b.expand = {};
        b.dom = {};
        b.init()
    }

    a.prototype = {
        init: function () {
            var b = this;
            b.dom = bds.se.skeleton();
            var d = $("#wrapper");
            b.dom.rsResult = d.find("#rs a");
            b.dom.hintResult = d.find(".se_common_hint");
            b.rs = b.dom.rsResult.length || 0;
            b.hint = b.dom.hintResult.length || 0;
            b.display.base = b.getBase();
            b.dom.L && b.getResult(b.dom.L);
            b.dom.R && b.getResult(b.dom.R);
            b.dom.T && b.getResult(b.dom.T);
            if (b.rs) {
                b.display.rs = b.getRS()
            }
            if (b.hint) {
                b.display.hint = b.getHint()
            }
            b.send()
        }, send: function () {
            var j = this;
            for (var m in j.display) {
                var g = {};
                g[m] = j.display[m];
                bds.log.send.sendPack("new_disp", g)
            }
            for (var l in j.expand) {
                if (l && j.expand[l]) {
                    for (var d in j.expand[l]) {
                        if (d && j.expand[l][d] && j.expand[l][d].length) {
                            var k = j.expand[l][d];
                            for (var b = 0; b < k.length; b++) {
                                var f = {};
                                f[l] = {expand: {}};
                                f[l].expand[d] = {};
                                f[l].expand[d][b] = k[b];
                                bds.log.send.sendPack("new_disp", f)
                            }
                        }
                    }
                }
            }
        }, getBase: function () {
            var b = this;
            var d = {};
            d.qid = bds.comm.qid || "";
            d.tpl = bds.comm.resTemplateName || "";
            d.async = bds.comm.supportis ? 1 : 0;
            d.page = bds.comm.pageNum || 1;
            d.upn = $.getCookie("BD_UPN") || "";
            b.dom.L && (d.left = b.dom.L.length);
            b.dom.R && (d.right = b.dom.R.length);
            b.dom.T && (d.top = b.dom.T.length);
            d.size = {};
            d.size.doc = {w: $(document).width(), h: $(document).height()};
            d.size.wind = {w: $(window).width(), h: $(window).height()};
            d.size.scr = {w: screen.width, h: screen.height};
            return d
        }, getRS: function () {
            var d = this;
            var b = {};
            b.num = d.rs;
            b.query = [];
            d.dom.rsResult.each(function (g) {
                var f = this.textContent || this.innerText;
                b.query.push(f)
            });
            return b
        }, getHint: function () {
            var b = this;
            var d = {};
            d.result = [];
            b.dom.hintResult.each(function (g) {
                var f = {};
                f.id = this.getAttribute("data-id") || 0;
                f.tpl = this.getAttribute("data-tpl") || "";
                d.result.push(f)
            });
            return d
        }, getResult: function (d) {
            var j = this, b = d;
            for (var g = 0, f = Math.min(b.length, 10);
                 g < f; g++) {
                var l = b[g].id, k = j.getResultDisplay(b[g]);
                j.expand[l] = k.expand;
                delete k.expand;
                j.display[l] = k
            }
        }, getResultDisplay: function (d) {
            var m = this;
            var s = d.dom, j = d.dataClick, g = {};
            g.id = j.p5 || "";
            g.srcid = j.rsv_srcid || s.attr("srcid") || 0;
            g.tpl = s.attr("tpl") || "";
            g.mu = j.mu || s.attr("mu") || "";
            g.fm = j.fm || "as";
            s.is(":hidden") && (g.show = 0);
            if (g.show == 0) {
                return g
            }
            g.size = r();
            g.pos = l();
            q() && (g.bdr = q());
            g.com = i();
            var f = n(), k = b(), o = p();
            if (f || k || o) {
                g.expand = {};
                if (f) {
                    g.link = f.length;
                    g.expand.links = f
                }
                if (k) {
                    g.img = k.length;
                    g.expand.imgs = k
                }
                if (o) {
                    g.app = o.length;
                    g.expand.apps = o
                }
            }
            return g;
            function r() {
                var t = d.size;
                return {w: t.width || 0, h: t.height || 0}
            }

            function l() {
                var t = d.pos;
                return {t: t.top || 0, l: t.left || 0}
            }

            function q() {
                if (j.rsv_bdr && j.rsv_bdr != 0) {
                    return j.rsv_bdr
                } else {
                    if (s.hasClass(".c-border") || s.find(".c-border").length) {
                        return 5
                    } else {
                        return 0
                    }
                }
            }

            function i() {
                var u = {};
                t(".favurl") && (u.fi = 1);
                t(".c-text-public.c-text-mult") && (u.gwi = 1);
                t(".icon-unsafe-icon") && (u.fxi = 1);
                t(".c-icon-v") && (u.vi = 1);
                t(".c-icon-med") && (u.yjji = 1);
                t(".c-icon-air") && (u.hxi = 1);
                t(".c-recommend") && (u.cr = 1);
                return u;
                function t(x) {
                    var w;
                    if (x) {
                        w = s.find(x)
                    }
                    if (w && w.length) {
                        return true
                    } else {
                        return false
                    }
                }
            }

            function n() {
                var w = s.find("a").not(":hidden").not("h3 a, .m");
                var t = [];
                var x = /^((https?:)?\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*(:\d+)*(\/.*)*/;
                var u = /^(\/s\?)/;
                w.each(function (B) {
                    var y = this.getAttribute("href");
                    if (y && x.test(y)) {
                        var z = y && y.match(/.*\/link\?url=([^&]*).*/);
                        if (z && z.length && z.length > 0 && z[1]) {
                            t.push(z[1])
                        } else {
                            t.push(y)
                        }
                    } else {
                        if (y && u.test(y)) {
                            t.push(y)
                        }
                    }
                });
                if (t.length) {
                    return t
                } else {
                    return false
                }
            }

            function b() {
                var t = [];
                var u = s.find("img").not(":hidden").not("[data-nolog]");
                if (u.length) {
                    u.each(function (x) {
                        var w = {w: this.width, h: this.height};
                        t.push({size: w})
                    });
                    return t
                } else {
                    return false
                }
            }

            function p() {
                var u = [];
                var t = s.find("object, video, audio");
                if (t.length) {
                    t.each(function (x) {
                        var w = $(this);
                        var y = {};
                        if (w.is("object") && w.attr("type") && w.attr("type").indexOf("flash") >= 0) {
                            y.type = 1
                        } else {
                            if (w.is("video")) {
                                y.type = 2
                            } else {
                                if (w.is("audio")) {
                                    y.type = 3
                                } else {
                                    y.type = 0
                                }
                            }
                        }
                        y.size = {w: w.width(), h: w.height()};
                        u.push(y)
                    });
                    return u
                } else {
                    return false
                }
            }
        }
    }
})();
(function () {
    function a() {
        this.pageElementsList = [];
        this.scrollTime = null;
        this.resizeTime = null;
        this.scrollTop = $(document).scrollTop();
        this.scrollLeft = $(document).scrollLeft();
        this.windowHeight = $(window).height();
        this.windowWidth = $(window).width()
    }

    a.prototype = {
        init: function () {
            var b = bds.se.skeleton();
            var d = this;
            $.each(["L", "R", "T"], function (f, g) {
                if (b[g]) {
                    $.merge(d.pageElementsList, d.getDom(b[g]));
                    d.bindEvent(b[g])
                }
            })
        }, getDom: function (b) {
            var d = [];
            $.each(b, function (f, j) {
                var g = {};
                g.top = j.pos.top;
                g.height = j.size.height;
                g.id = j.id;
                g.visible = 0;
                d.push(g)
            });
            return d
        }, bindEvent: function (b) {
            $.each(b, function (g, l) {
                var k = 200;
                var j = false;
                var f = null;
                l.dom.bind("mouseenter.useraction", function () {
                    if (f !== null) {
                        clearTimeout(f)
                    }
                    f = setTimeout(function () {
                        bds.log.send.sendPack("new_view", {type: "mouseIn", id: l.id, t: new Date().getTime()});
                        j = true;
                        f = null
                    }, k)
                }).bind("mouseleave.useraction", function () {
                    if (f !== null) {
                        clearTimeout(f);
                        f = null
                    }
                    if (j) {
                        bds.log.send.sendPack("new_view", {type: "mouseOut", id: l.id, t: new Date().getTime()});
                        j = false
                    }
                })
            })
        }, destroy: function () {
            $(window).unbind(".useraction");
            this.pageElementsList.splice(0, this.pageElementsList.length)
        }, sight: function () {
            var b = this;
            $.each(this.pageElementsList, function (d, g) {
                var f = (b.scrollTop < g.top + g.height) && (b.scrollTop + b.windowHeight > g.top);
                if (g.visible === 1 && !f) {
                    bds.log.send.sendPack("new_view", {
                        type: "sight",
                        resid: g.id,
                        action: "out",
                        t: new Date().getTime()
                    });
                    g.visible = 0
                } else {
                    if (g.visible === 0 && f) {
                        bds.log.send.sendPack("new_view", {
                            type: "sight",
                            resid: g.id,
                            action: "in",
                            t: new Date().getTime()
                        });
                        g.visible = 1
                    }
                }
            })
        }, collectPoint: function (d, g) {
            var f = d + "Time";
            var i = this;
            if (i[f] === null) {
                i[f] = setTimeout(function () {
                    bds.log.send.sendPack("new_view", b(d));
                    i.sight();
                    i[f] = null
                }, 1000)
            }
            function b(j) {
                if (j === "resize") {
                    var l = $(window);
                    i.windowHeight = l.height();
                    i.windowWidth = l.width();
                    return {type: "resize", t: new Date().getTime(), height: i.windowHeight, width: i.windowWidth}
                } else {
                    if (j === "scroll") {
                        var k = $(document);
                        i.scrollTop = k.scrollTop();
                        i.scrollLeft = k.scrollLeft();
                        return {type: "scroll", t: new Date().getTime(), offsetX: i.scrollTop, offsetY: i.scrollLeft}
                    }
                }
            }
        }, collect: function () {
            this.init();
            var b = this;
            if (this.resizeTime !== null) {
                clearTimeout(this.resizeTime)
            }
            this.resizeTime = null;
            if (this.scrollTime !== null) {
                clearTimeout(this.scrollTime)
            }
            this.scrollTime = null;
            $(window).bind("focus.useraction", function () {
                bds.log.send.sendPack("new_view", {type: "focus", t: new Date().getTime()})
            }).bind("blur.useraction", function () {
                bds.log.send.sendPack("new_view", {type: "blur", t: new Date().getTime()})
            }).bind("resize.useraction", function (d) {
                b.collectPoint("resize", d)
            }).bind("scroll.useraction", function (d) {
                b.collectPoint("scroll", d)
            });
            this.sight()
        }, outInterface: function () {
            var b = this;
            return {
                collect: function () {
                    b.collect()
                }, destroy: function () {
                    b.destroy()
                }
            }
        }
    };
    bds.se.userAction = new a().outInterface()
})();
function formatDate(a, d) {
    var b = function (f) {
        return f > 9 ? f : "0" + f
    };
    if (typeof(a) == "number" || typeof(a) == "string") {
        a = new Date(a)
    }
    return [a.getFullYear(), b(a.getMonth() + 1), b(a.getDate())].join(d || "")
}
function baseChangeUrl(a) {
    if (bds.comm.search_tool.st && bds.comm.search_tool.et && bds.comm.search_tool.stftype) {
        if (a.indexOf("&gpc=") < 0) {
            a += "&gpc=" + encodeURIComponent("stf=" + bds.comm.search_tool.st + "," + bds.comm.search_tool.et + "|stftype=" + bds.comm.search_tool.stftype + "")
        }
    }
    if (bds.comm.search_tool.si) {
        if (a.indexOf("&si=") < 0) {
            a += "&si=" + encodeURIComponent(bds.comm.search_tool.si) + "&ct=2097152"
        }
    }
    if (bds.comm.search_tool.lang) {
        if (a.indexOf("&lang=") < 0) {
            a += "&lang=" + encodeURIComponent(bds.comm.search_tool.lang);
            a += "&rsv_rq=" + encodeURIComponent(bds.comm.search_tool.lang)
        }
    }
    changeUrl(a)
}
function langChangeUrl(a, d, b) {
    ns_c({fm: "advTool", qid: bds.comm.qid, title: encodeURI(b), rsv_advTool_lang: d});
    baseChangeUrl("wd=" + encodeURIComponent($("#kw").val()) + "&" + a + "=" + encodeURIComponent(d) + "&rsv_rq=" + encodeURIComponent(d))
}
function advChangeUrl(d, g, f, a) {
    if (g.indexOf("=") != -1) {
        var b = 1
    } else {
        var b = 0
    }
    ns_c({
        fm: "advTool",
        qid: bds.comm.qid,
        title: encodeURI(f),
        rsv_advTool_time: a,
        rsv_advTool_stet: g.substr(4).replace(",", "_")
    });
    baseChangeUrl("wd=" + encodeURIComponent($("#kw").val()) + "&" + d + "=" + encodeURIComponent(g) + "&tfflag=" + b)
}
function fileChangeUrl(d, b, a) {
    ns_c({fm: "advTool", qid: bds.comm.qid, title: encodeURI(b), rsv_advTool_ft: a});
    baseChangeUrl("wd=" + encodeURIComponent(queryReplace("filetype", d)))
}
function queryReplace(f, d) {
    if (f && (f == "filetype" || f == "site")) {
        var b = new RegExp("(" + f + "):[^\\s]*[ ]?");
        var a = $("#kw").val();
        if (d == " " || d == null) {
            return a.replace(b, "")
        } else {
            if (a.match(b)) {
                return a.replace(b, "$1:" + d + " ")
            } else {
                return f + ":" + d + " " + a
            }
        }
    } else {
        return a
    }
}
function extChangeUrl(a) {
    if (a) {
        ns_c({fm: "advTool", qid: bds.comm.qid, title: encodeURI("精确匹配"), rsv_advTool_ext: 1});
        baseChangeUrl('wd="' + encodeURIComponent($("#kw").val()) + '"')
    } else {
        ns_c({fm: "advTool", qid: bds.comm.qid, title: encodeURI("智能匹配"), rsv_advTool_ext: 0});
        baseChangeUrl("wd=" + encodeURIComponent($("#kw").val().replace(/^\"(.*)\"$/, "$1")))
    }
}
$(window).on("swap_end", function () {
    bds.comm.search_tool && (bds.comm.search_tool.init = false)
});
$(window).on("swap_begin", function () {
    $(document).off("click.searchTool")
});
var langfilterTip, timefilterTip, fileTypeTip, insideSearchTip;
$(document).delegate(".head_nums_cont_outer", "mousedown", function (b) {
    if (typeof(bds.comm.search_tool) != "undefined") {
        if (bds.comm.search_tool.init) {
            return
        }
        bds.comm.search_tool.init = true;
        var f = $(this), q = f.find(".search_tool").eq(0), d = f.find(".search_tool_close").eq(0), a = f.find(".head_nums_cont_inner").eq(0);
        q.on("click", function () {
            a.animate({top: 0}, 250);
            ns_c({fm: "advTool", qid: bds.comm.qid, title: encodeURI("搜索工具"), rsv_advTool: 0})
        });
        d.on("click", function () {
            a.animate({top: -42}, 250, function () {
                if (bds.comm.search_tool.lang == "en" || bds.comm.search_tool.st || bds.comm.search_tool.et || bds.comm.search_tool.si || bds.comm.search_tool.ft || bds.comm.search_tool.exact) {
                    ns_c({fm: "advTool", qid: bds.comm.qid, title: encodeURI("清除"), rsv_advTool: 2});
                    baseChangeUrl("wd=" + encodeURIComponent($("#kw").val().replace(/(filetype:[^\s]* )|(site:[^\s]*)/g, "").replace(/^\"+(.+)\"+$/, "$1")) + "&lang=&rsv_rq=&ct=0&si=&tfflag=0&gpc=" + encodeURIComponent("stf="));
                    $("input[name='gpc'],input[name='si'],input[name='ct']", "form").val("")
                } else {
                    ns_c({fm: "advTool", qid: bds.comm.qid, title: encodeURI("收起工具"), rsv_advTool: 1})
                }
            })
        });
        var p = f.find(".search_tool_la").eq(0);
        if (p.length > 0) {
            var g = "<div class='c-tip-menu c-tip-langfilter'><ul>";
            if (bds.comm.search_tool.lang == "en") {
                g += "<li><span>更多英文结果</span></li>";
                g += "<li><a href='javascript:;' onClick='langChangeUrl(\"lang\",\"cn\",this.innerHTML)'>默认结果</a></li>"
            } else {
                if (bds.comm.search_tool.lang == "cn") {
                    g += "<li><a href='javascript:;' onClick='langChangeUrl(\"lang\",\"en\",this.innerHTML)'>更多英文结果</a></li>";
                    g += "<li><span>默认结果</span></li>"
                }
            }
            g += "</li></ul></div>";
            langfilterTip = new bds.se.tip({
                target: p,
                mode: "none",
                content: $(g),
                arrow: {has: 0, offset: 0},
                offset: {x: 15, y: 21}
            });
            langfilterTip.hide()
        }
        var n = f.find(".search_tool_tf").eq(0);
        if (n.length > 0) {
            var t = "<div class='c-tip-menu c-tip-timerfilter'><ul>";
            if (!bds.comm.search_tool.st && !bds.comm.search_tool.et) {
                t += " <li><span>时间不限</span></li>"
            } else {
                t += " <li><a href='javascript:;' onClick='advChangeUrl(\"gpc\",\"stf\",this.innerHTML,0)'>时间不限</a></li>"
            }
            if (bds.comm.search_tool.st >= bds.comm.search_tool.thisDay && bds.comm.search_tool.stftype == "1") {
                t += " <li><span>一天内</span></li>"
            } else {
                t += " <li><a href='javascript:;' onClick='advChangeUrl(\"gpc\",\"stf=" + bds.comm.search_tool.oneDay + "," + bds.comm.serverTime + "|stftype=1\",this.innerHTML,1)'>一天内</a></li>"
            }
            if (bds.comm.search_tool.st >= bds.comm.search_tool.thisWeek && bds.comm.search_tool.st < bds.comm.search_tool.thisDay && bds.comm.search_tool.stftype == "1") {
                t += " <li><span>一周内</span></li>"
            } else {
                t += " <li><a href='javascript:;' onClick='advChangeUrl(\"gpc\",\"stf=" + bds.comm.search_tool.oneWeek + "," + bds.comm.serverTime + "|stftype=1\",this.innerHTML,2)'>一周内</a></li>"
            }
            if (bds.comm.search_tool.st >= bds.comm.search_tool.thisMonth && bds.comm.search_tool.st < bds.comm.search_tool.thisWeek && bds.comm.search_tool.stftype == "1") {
                t += " <li><span>一月内</span></li>"
            } else {
                t += " <li><a href='javascript:;' onClick='advChangeUrl(\"gpc\",\"stf=" + bds.comm.search_tool.oneMonth + "," + bds.comm.serverTime + "|stftype=1\",this.innerHTML,3)'>一月内</a></li>"
            }
            if (bds.comm.search_tool.st >= bds.comm.search_tool.thisYear && bds.comm.search_tool.st < bds.comm.search_tool.thisMonth && bds.comm.search_tool.stftype == "1") {
                t += " <li><span>一年内</span></li>"
            } else {
                t += " <li><a href='javascript:;' onClick='advChangeUrl(\"gpc\",\"stf=" + bds.comm.search_tool.oneYear + "," + bds.comm.serverTime + "|stftype=1\",this.innerHTML,4)'>一年内</a></li>"
            }
            t += " <li class='c-tip-custom'>";
            t += " <hr />自定义";
            t += " <p class='c-tip-custom-st'>从<input name='st' date-min='0' date-max='" + formatDate(bds.comm.serverTime * 1000) + "' type='txt' autocomplete='off' ";
            if (bds.comm.search_tool.st && bds.comm.search_tool.et && bds.comm.search_tool.stftype == "2") {
                t += "value='" + formatDate(bds.comm.search_tool.st * 1000, "-") + "' data-value='" + bds.comm.search_tool.st * 1000 + "' class='c-tip-custom-input'/></p>"
            } else {
                t += "value='" + formatDate(bds.comm.serverTime * 1000, "-") + "' data-value='' class='c-tip-custom-input c-tip-custom-input-init'/></p>"
            }
            t += "  <p class='c-tip-custom-et'>至<input name='et' date-min='0' date-max='" + formatDate(bds.comm.serverTime * 1000) + "' type='txt' autocomplete='off' ";
            if (bds.comm.search_tool.st && bds.comm.search_tool.et && bds.comm.search_tool.stftype == "2") {
                t += "value='" + formatDate(bds.comm.search_tool.et * 1000, "-") + "' data-value='" + bds.comm.search_tool.et * 1000 + "' class='c-tip-custom-input'/></p>"
            } else {
                t += "value='" + formatDate(bds.comm.serverTime * 1000, "-") + "' data-value='' class='c-tip-custom-input c-tip-custom-input-init'/></p>"
            }
            t += "<div class='c-tip-timerfilter-custom-error'>自定义时间错误！</div>";
            t += "<a href='javascript:;' class='c-tip-custom-submit'>确认</a>";
            t += "</li></ul></div>";
            timefilterTip = new bds.se.tip({
                target: n,
                mode: "none",
                content: $(t),
                arrow: {has: 0, offset: 0},
                offset: {x: 15, y: 21},
                onShow: function () {
                    if ($(this.getTarget()).width() > 95) {
                        $("ul", this.getDom()).width($(this.getTarget()).width() + 20)
                    }
                    $(".c-tip-custom-input").on("click", function (D) {
                        var E = this, y = null, x = new Date(), C = $(E).parents(".c-tip-custom"), w = C.find("input[name='st']"), z = C.find("input[name='et']");
                        if ($(E).attr("data-value")) {
                            x.setTime($(E).attr("data-value"))
                        }
                        $(E).parents(".c-tip-custom").find(".c-tip-custom-input").removeClass("c-tip-custom-input-focus");
                        $(E).addClass("c-tip-custom-input-focus");
                        if ($("#c-tip-custom-calenderCont").length == 0) {
                            $(E).parents(".c-tip-custom").append("<div id='c-tip-custom-calenderCont'></div>")
                        }
                        $("#c-tip-custom-calenderCont").html("");
                        var B = {
                            element: "c-tip-custom-calenderCont",
                            date: formatDate(x),
                            between: [$(E).attr("date-min") - 0, $(E).attr("date-max") - 0],
                            onSelectDay: function (H, I) {
                                H += "";
                                if (E.name == "st") {
                                    var F = new Date(H.substr(0, 4), H.substr(4, 2) - 1, H.substr(6, 2), 0, 0, 0);
                                    z.attr("date-min", H)
                                } else {
                                    var F = new Date(H.substr(0, 4), H.substr(4, 2) - 1, H.substr(6, 2), 23, 59, 59);
                                    w.attr("date-max", H)
                                }
                                $(E).val(formatDate(F, "-"));
                                $(E).attr("data-value", F.getTime());
                                $("#c-tip-custom-calenderCont").hide();
                                $(E).removeClass("c-tip-custom-input-focus").removeClass("c-tip-custom-input-init")
                            }
                        };
                        if (typeof(WCal) == "undefined") {
                            $.getScript("https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/plugins/new_wcal_caae086b.js", function () {
                                y = new WCal(B);
                                if (x) {
                                    y.setDay(formatDate(x), function (F) {
                                        F.className += " op_mon_day_selected"
                                    })
                                }
                            })
                        } else {
                            y = new WCal(B);
                            if (x) {
                                y.setDay(formatDate(x), function (F) {
                                    F.className += " op_mon_day_selected"
                                })
                            }
                        }
                        $("#c-tip-custom-calenderCont").css({
                            top: $(this).position().top - 2,
                            left: $(this).position().left + $(this).width() + 15,
                            display: "block"
                        });
                        D.stopPropagation()
                    });
                    $(".c-tip-custom-input").on("focus", function (w) {
                        $(this).removeClass("c-tip-custom-input-init")
                    });
                    $(".c-tip-custom-input").on("blur", function (x) {
                        var z = this;

                        function y(D) {
                            var C = /^\s*(\d{4})-(\d\d)-(\d\d)\s*$/, B = new Date(NaN), F, E = C.exec(D);
                            if (E) {
                                F = +E[2];
                                B.setFullYear(E[1], F - 1, E[3]);
                                if (F != B.getMonth() + 1) {
                                    B.setTime(NaN)
                                }
                            }
                            return B
                        }

                        var w = y($(z).val());
                        if (w instanceof Date && w.getTime()) {
                            $(z).attr("data-value", w.getTime());
                            $(".c-tip-timerfilter-custom-error").hide()
                        } else {
                            if ($(z).val() == "") {
                                $(z).attr("data-value", "0");
                                $(".c-tip-timerfilter-custom-error").hide()
                            } else {
                                $(z).attr("data-value", "");
                                $(".c-tip-timerfilter-custom-error").show()
                            }
                        }
                    });
                    try {
                        $(".c-tip-custom-submit").off("click.searchTool").on("click.searchTool", function (y) {
                            var B = this, z = $(B).parents(".c-tip-custom"), w = parseInt($(".c-tip-custom-input", z)[0].getAttribute("data-value") / 1000), x = parseInt($(".c-tip-custom-input", z)[1].getAttribute("data-value") / 1000);
                            $("#c-tip-custom-calenderCont").hide();
                            if (w == "" || !w) {
                                w = 0
                            }
                            if ((x == "" || !x) && w && w != "") {
                                x = parseInt((new Date()).setHours(23, 59, 58) / 1000)
                            }
                            if (x > bds.comm.serverTime) {
                                if (w <= 0) {
                                    w = "", x = ""
                                } else {
                                    x = parseInt((new Date()).setHours(23, 59, 58) / 1000)
                                }
                            }
                            if (w > x || w > bds.comm.serverTime) {
                                $(".c-tip-timerfilter-custom-error").show();
                                y.stopPropagation();
                                return
                            }
                            if (w == 0 && x == 0) {
                                w = "", x = ""
                            }
                            $(".c-tip-timerfilter-custom-error").hide();
                            advChangeUrl("gpc", "stf=" + w + "," + x + "|stftype=2", "自定义时间:" + w + "|" + x, 5)
                        })
                    } catch (u) {
                    }
                }
            });
            timefilterTip.hide()
        }
        var m = f.find(".search_tool_ft").eq(0);
        if (m.length > 0) {
            var l = "<div class='c-tip-menu c-tip-timerfilter c-tip-timerfilter-ft'><ul>";
            if (!bds.comm.search_tool.ft) {
                l += " <li><span>所有网页和文件(不限格式)</span></li>"
            } else {
                l += " <li><a href='javascript:;' onClick='fileChangeUrl(null,this.innerHTML,0)'>所有网页和文件(不限格式)</a></li>"
            }
            if (bds.comm.search_tool.ft == "pdf") {
                l += " <li><span>Adobe Acrobat PDF(.pdf)</span></li>"
            } else {
                l += " <li><a href='javascript:;' onClick='fileChangeUrl(\"pdf\",this.innerHTML,1)'>Adobe Acrobat PDF(.pdf)</a></li>"
            }
            if (bds.comm.search_tool.ft == "doc") {
                l += " <li><span>微软 Word(.doc)</span></li>"
            } else {
                l += " <li><a href='javascript:;' onClick='fileChangeUrl(\"doc\",this.innerHTML,2)'>微软 Word(.doc)</a></li>"
            }
            if (bds.comm.search_tool.ft == "xls") {
                l += " <li><span>微软 Excel(.xls)</span></li>"
            } else {
                l += " <li><a href='javascript:;' onClick='fileChangeUrl(\"xls\",this.innerHTML,3)'>微软 Excel(.xls)</a></li>"
            }
            if (bds.comm.search_tool.ft == "ppt") {
                l += " <li><span>微软 PowerPoint(.ppt)</span></li>"
            } else {
                l += " <li><a href='javascript:;' onClick='fileChangeUrl(\"ppt\",this.innerHTML,4)'>微软 PowerPoint(.ppt)</a></li>"
            }
            if (bds.comm.search_tool.ft == "rtf") {
                l += " <li><span>RTF 文件(.rtf)</span></li>"
            } else {
                l += " <li><a href='javascript:;' onClick='fileChangeUrl(\"rtf\",this.innerHTML,5)'>RTF 文件(.rtf)</a></li>"
            }
            l += "</ul></div>";
            var o = new bds.se.tip({
                target: m,
                mode: "none",
                content: $(l),
                arrow: {has: 0, offset: 0},
                offset: {x: 15, y: 21}
            });
            o.hide()
        }
        var k = f.find(".search_tool_si").eq(0);
        if (k.length > 0) {
            insideSearchTip = new bds.se.tip({
                target: k,
                mode: "none",
                content: $("<div class='c-tip-menu c-tip-timerfilter c-tip-timerfilter-si'><ul> <li><input name='si' type='txt' class='c-tip-si-input c-gap-bottom-small c-gap-right-small' autocomplete='off' value='" + bds.comm.search_tool.si + "' placeholder='例如:baidu.com' /><a href='javascript:;' class='c-tip-timerfilter-si-submit'>确认</a></li> <li><p class='c-tip-timerfilter-si-error'>无法识别，正确格式：baidu.com</p></li></ul></div>"),
                arrow: {has: 0, offset: 0},
                offset: {x: 15, y: 21},
                onShow: function () {
                    $(".c-tip-si-input").on("focus", function (w) {
                        $(this).addClass("c-tip-si-input-focus")
                    });
                    $(".c-tip-si-input").on("blur", function (w) {
                        $(this).removeClass("c-tip-si-input-focus")
                    });
                    try {
                        $(".c-tip-timerfilter-si-submit").off("click.searchTool").on("click.searchTool", function (y) {
                            var B = this, z = $(B).parents(".c-tip-timerfilter-si"), w = $("input", z).val(), x = queryReplace("site");
                            if (w == "") {
                                ns_c({
                                    fm: "advTool",
                                    qid: bds.comm.qid,
                                    title: encodeURI("站内检索:" + w),
                                    rsv_advTool_si: encodeURI(w)
                                });
                                baseChangeUrl("wd=" + encodeURIComponent(x) + "&si=&ct=0")
                            } else {
                                if (w.match(/^[\w\-_]+(\.[\w\-_]+)+$/)) {
                                    $(".c-tip-timerfilter-si-error").hide();
                                    ns_c({
                                        fm: "advTool",
                                        qid: bds.comm.qid,
                                        title: encodeURI("站内检索:" + w),
                                        rsv_advTool_si: encodeURI(w)
                                    });
                                    baseChangeUrl("wd=" + encodeURIComponent(x) + "&si=" + encodeURIComponent(w) + "&ct=2097152")
                                } else {
                                    $(".c-tip-timerfilter-si-error").show();
                                    y.stopPropagation();
                                    y.preventDefault();
                                    return false
                                }
                            }
                        })
                    } catch (u) {
                    }
                }
            });
            insideSearchTip.hide()
        }
        var j = true;
        p.on("click", function (u) {
            if (j) {
                langfilterTip && langfilterTip.show();
                j = false;
                timefilterTip && timefilterTip.hide();
                i = true;
                o && o.hide();
                s = true;
                insideSearchTip && insideSearchTip.hide();
                r = true;
                ns_c({fm: "advTool", qid: bds.comm.qid, title: encodeURI("语言筛选浮层展现"), rsv_advTool_tip: 1});
                $(document).on("click.searchTool", function (w) {
                    if ($(w.target).parents(".c-tip-langfilter").length == 0 && langfilterTip) {
                        langfilterTip.hide();
                        j = true;
                        $(document).off("click.searchTool")
                    }
                })
            } else {
                langfilterTip && langfilterTip.hide();
                j = true;
                $(document).off("click.searchTool")
            }
            u.stopPropagation()
        });
        var i = true;
        n.on("click", function (u) {
            if (i) {
                langfilterTip && langfilterTip.hide();
                j = true;
                timefilterTip && timefilterTip.show();
                i = false;
                o && o.hide();
                s = true;
                insideSearchTip && insideSearchTip.hide();
                r = true;
                ns_c({fm: "advTool", qid: bds.comm.qid, title: encodeURI("时间筛选浮层展现"), rsv_advTool_tip: 0});
                $(document).on("click.searchTool", function (w) {
                    if ($(w.target).parents(".c-tips-container,#c-tip-custom-calenderCont").length == 0 && timefilterTip) {
                        timefilterTip.hide();
                        $("#c-tip-custom-calenderCont").hide();
                        timefilterTip.getDom().find(".c-tip-custom-input-focus").removeClass("c-tip-custom-input-focus");
                        i = true;
                        $(document).off("click.searchTool")
                    }
                })
            } else {
                timefilterTip && timefilterTip.hide();
                i = true;
                $(document).off("click.searchTool")
            }
            u.stopPropagation()
        });
        var s = true;
        m.on("click", function (u) {
            if (s) {
                langfilterTip && langfilterTip.hide();
                j = true;
                timefilterTip && timefilterTip.hide();
                i = true;
                o && o.show();
                s = false;
                insideSearchTip && insideSearchTip.hide();
                r = true;
                ns_c({fm: "advTool", qid: bds.comm.qid, title: encodeURI("网页格式浮层展现"), rsv_advTool_tip: 2});
                $(document).on("click.searchTool", function (w) {
                    if ($(w.target).parents(".c-tip-timerfilter-ft").length == 0 && o) {
                        o.hide();
                        s = true;
                        $(document).off("click.searchTool")
                    }
                })
            } else {
                o && o.hide();
                s = true;
                $(document).off("click.searchTool")
            }
            u.stopPropagation()
        });
        var r = true;
        k.on("click", function (u) {
            if (r) {
                langfilterTip && langfilterTip.hide();
                j = true;
                timefilterTip && timefilterTip.hide();
                i = true;
                o && o.hide();
                s = true;
                insideSearchTip && insideSearchTip.show();
                r = false;
                ns_c({fm: "advTool", qid: bds.comm.qid, title: encodeURI("站内搜索浮层展现"), rsv_advTool_tip: 3});
                $(document).on("click.searchTool", function (w) {
                    if ($(w.target).parents(".c-tip-timerfilter-si").length == 0 && insideSearchTip) {
                        insideSearchTip.hide();
                        r = true;
                        $(document).off("click.searchTool")
                    }
                })
            } else {
                insideSearchTip && insideSearchTip.hide();
                r = true;
                $(document).off("click.searchTool")
            }
            u.stopPropagation()
        })
    }
});
$(window).on("swap_begin", function () {
    if (bds && bds.se && bds.se.displayTime !== undefined && bds.se.displayTime !== null) {
        clearTimeout(bds.se.displayTime);
        bds.se.displayTime = null
    }
}).on("confirm", function () {
    if (bds && bds.comm && bds.comm.globalLogFlag && bds.comm.globalLogFlag == 1) {
        if (bds.comm.logFlagNoNetwork == 1 || bds.comm.logFlagNoIntegration == 1) {
        } else {
            bds.se.displayTime = setTimeout(function () {
                bds && bds.se && bds.se.display();
                bds.se.displayTime = null
            }, 5000);
            bds.se.userAction.collect()
        }
    }
}).on("swap_end", function (b, a) {
    if (!a && bds && bds.comm && bds.comm.globalLogFlag && bds.comm.globalLogFlag == 1) {
        if (bds.comm.logFlagNoNetwork == 1 || bds.comm.logFlagNoIntegration == 1) {
        } else {
            bds.se.displayTime = setTimeout(function () {
                bds && bds.se && bds.se.display();
                bds.se.displayTime = null
            }, 5000);
            bds.se.userAction.collect()
        }
    }
});
$(window).on("swap_end", function () {
    if (bds.comm.__rdNum && bds.comm.__rdNum > 9000) {
        setTimeout(function () {
            $.ajax({
                dataType: "script",
                cache: true,
                url: "https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/plugins/clean_8a43b779.js",
                success: function () {
                    bds.se.cleanCookie.init()
                }
            })
        }, 0)
    }
});
(function () {
    if (!location.href.match(/voice=1/) && !navigator.userAgent.match(/mac os x/i) && !window.__sam_voice_flash) {
        return
    }
    require.config({paths: {Recorder: "https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/voice/js/voice_3f76ec0c.js".replace(/\.js$/, "")}});
    require.config({paths: {swfobject: "https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/static/protocol/https/plugins/swfobject_c1c7185a.js".replace(/\.js$/, "")}});
    function a(i) {
        var g = ["voice_beha=1"], f = window.bds && bds.util && bds.util.domain ? bds.util.domain.get("http://nsclick.baidu.com") : "http://nsclick.baidu.com";
        for (var d in i) {
            if (i.hasOwnProperty(d)) {
                g.push(d + "=" + i[d])
            }
        }
        var b = window["nsIMG" + (+new Date())] = new Image();
        b.src = f + "/v.gif?pid=201&" + g.join("&");
        return true
    }

    require(["swfobject", "Recorder"], function (d, b) {
        b.log = a;
        if (!b || !b.support()) {
            return
        }
        b.addStyle();
        window.__supportvoice = true;
        var f = $("#form .ipt_rec");
        f.css("display", "block");
        f.click(function () {
            var g = b.init({url: bds.util.domain.get("http://vse.baidu.com") + "/echo.fcgi"});
            g.done(function (i) {
                i.openUI();
                i.onfinish(function (j) {
                    var l = j.content.item[0];
                    var k = (j && j.result) ? j.result.corpus_no : "";
                    changeUrl("wd=" + encodeURIComponent(l) + "&rsv_voice=1&rsv_vcorpus=" + encodeURIComponent(k));
                    bds.comm.lastVoiceQuery = l
                });
                b.log({q: "resolve"})
            }).fail(function () {
                b.log({q: "reject"});
                alert("不能获得麦克风的权限")
            });
            b.log({q: "start"})
        })
    })
})();