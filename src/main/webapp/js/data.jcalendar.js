(function (f) {
    var d = true;
    var c = f('<div class="cal-wrap" style="z-index:30000;display:none;position: absolute;left: 23px;top: 23px; ">' + '<div class="cal">' + '<div class="cal-top">' + '<a href="javascript:void(0);" class="first"></a>' + '<a href="javascript:void(0);" class="prev"></a>' + '<div class="month">' + '<input type="text" value="" readonly="readonly" disabled="disabled"/>' + '<ul class="time-list">' + "<li>一月</li><li>二月</li><li>三月</li><li>四月</li><li>五月</li><li>六月</li>" + "<li>七月</li><li>八月</li><li>九月</li><li>十月</li><li>十一月</li><li>十二月</li>" + "</ul>" + "</div>" + '<div class="year">' + '<input type="text" value="" readonly="readonly" disabled="disabled"/>' + '<div class="time-list">' + '<ul class="clearfix">' + "<li>2016</li>" + "</ul>" + '<div class="time-list-ft"><a href="javascript:void(0);" class="fl">←</a><a href="javascript:void(0);" class="fr">→</a><a href="javascript:void(0);" class="close">×</a></div>' + "</div>" + "</div>" + '<a href="javascript:void(0);" class="last"></a>' + '<a href="javascript:void(0);" class="next"></a>' + "</div>" + '<ul class="cal-week">' + "<li><b>日</b></li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li><b>六</b></li>" + "</ul>" + '<div class="cal-cm"></div>' + "</div>" + '<div class="cal cal-right">' + '<div class="cal-top">' + '<a href="javascript:void(0);" class="last"></a>' + '<a href="javascript:void(0);" class="next"></a>' + '<div class="year">' + '<input type="text" value="" readonly="readonly" disabled="disabled"/>' + '<div class="time-list">' + '<ul class="clearfix">' + "<li>2016</li>" + "</ul>" + '<div class="time-list-ft"><a href="javascript:void(0);" class="fl">←</a><a href="javascript:void(0);" class="fr">→</a><a href="javascript:void(0);" class="close">×</a></div>' + "</div>" + "</div>" + '<div class="month">' + '<input type="text" value="" readonly="readonly" disabled="disabled"/>' + '<ul class="time-list">' + "<li>一月</li><li>二月</li><li>三月</li><li>四月</li><li>五月</li><li>六月</li>" + "<li>七月</li><li>八月</li><li>九月</li><li>十月</li><li>十一月</li><li>十二月</li>" + "</ul>" + "</div>" + "</div>" + '<ul class="cal-week">' + "<li><b>日</b></li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li><b>六</b></li>" + "</ul>" + '<div class="cal-cm"></div>' + "</div>" + '<div class="cal-ft"><a href="javascript:void(0);" class="cal-btn">今天</a></div>' + "</div>");
    var g = f(c);
    f(document.body).append(g);
    var b = g.find("div");
    var a = g.find("a");
    var e = g.find("input");
    var h = g.find("ul");
    f.jcalendar = function (ay, ak) {
        var ag = this;
        ag.$el = f(ay);
        ag.el = ay;
        ag.options = f.extend({}, f.jcalendar.defaultOptions, ak);
        var y = ag.el.selector;
        var ar = {closeView: ag.options.closeCalendar};
        var Y = ag.options.onpicked;
        f(y)[0].onchange = Y;
        var al = ag.options.isSingle;
        var I = ag.options.showFormat;
        var ac = ag.options.formatBeforeInfo;
        var s = ag.options.formatAfterInfo;
        var F = ag.options.startDate;
        F = F ? F : "1901-01-01";
        var ai = ag.options.endDate;
        ai = ai ? ai : "2050-12-31";
        F = F.substring(0, 4) + "/" + F.substring(5, 7) + "/" + F.substring(8, 10);
        ai = ai.substring(0, 4) + "/" + ai.substring(5, 7) + "/" + ai.substring(8, 10);
        var l = ag.options.isTodayBlock;
        var k = ag.options.todayClickColor;
        var ao = ag.options.noClickColor;
        var az = ag.options.restColor;
        var ae = ag.options.noRestColor;
        var aj = ag.options.clickByYearMonth;
        var K = ag.options.lunarColor;
        var ap = ag.options.isTwoRows;
        var aA = ag.options.isYearMonthDisabled;
        var o = ag.options.condition;

        function C(i) {
            return document.getElementById(i)
        }

        function u(i) {
            return document.createElement(i)
        }

        function at(D, B, A, L) {
            var E = new q(new Date(D, B, 1));
            var H = new q(new Date(A, L, 1));
            v.init(E, 0);
            x.draw(1);
            v.init(H, 1);
            x.draw(0);
            x.resetYM(E, H)
        }

        function an(i) {
            i = ac ? i.replace(ac, "") : i;
            i = s ? i.replace(s, "") : i;
            return i
        }

        function af() {
            if (o[0] && f(o[1]).attr("class") == o[2]) {
                aw(g, o[3], false)
            }
            if (m()) {
                g[0].children[2].children[0].style.color = ao
            } else {
                g[0].children[2].children[0].style.color = "#297405"
            }
            am(g, f(y).val())
        }

        function m() {
            var D = new Date(F);
            var i = new Date(ai);
            var B = new Date();
            var A = new Date(B.getFullYear(), B.getMonth(), B.getDate());
            return A > i || A < D
        }

        function aw(R, Q, M) {
            Q = an(Q);
            var N = R[0].children[0].children[0].children[3].children[0].value;
            var H = aB(R[0].children[0].children[0].children[2].children[0].value);
            var D = R[0].children[0].children[2].children;
            var A = R[0].children[1].children[2].children;
            for (var E in D) {
                if (D[E].children) {
                    var P = D[E].children[0].numHTML;
                    var B = new Date(N, H - 1, P);
                    var i = new Date(Q.substring(0, 4), Q.substring(5, 7) - 1, Q.substring(8, 10));
                    var L = M ? B < i : B > i;
                    if (L) {
                        D[E].children[0].style.color = ao;
                        if (ap == "2") {
                            D[E].children[1].style.color = ao
                        }
                        D[E].onclick = null;
                        D[E].style.cursor = "auto"
                    }
                }
            }
            for (var E in A) {
                if (A[E].children) {
                    var P = A[E].children[0].numHTML;
                    var B = new Date(N, H, P);
                    var i = new Date(Q.substring(0, 4), Q.substring(5, 7) - 1, Q.substring(8, 10));
                    var L = M ? B < i : B > i;
                    if (L) {
                        A[E].children[0].style.color = ao;
                        if (ap == "2") {
                            D[E].children[1].style.color = ao
                        }
                        A[E].onclick = null;
                        A[E].style.cursor = "auto"
                    }
                }
            }
        }

        function am(P, N) {
            N = an(N);
            if (N && N.length >= 10) {
                N = N.substring(0, 10);
                var L = P[0].children[0].children[0].children[3].children[0].value;
                var H = aB(P[0].children[0].children[0].children[2].children[0].value);
                var D = P[0].children[0].children[2].children;
                var A = P[0].children[1].children[2].children;
                for (var E in D) {
                    if (D[E].children) {
                        var M = D[E].children[0].numHTML;
                        var B = new Date(L, H - 1, M);
                        var i = new Date(N.substring(0, 4), N.substring(5, 7) - 1, N.substring(8, 10));
                        if (B.getTime() == i.getTime()) {
                            D[E].style.border = "1px solid #a5b9da";
                            D[E].style.background = k
                        } else {
                            D[E].style.border = "";
                            D[E].style.background = ""
                        }
                    }
                }
                for (var E in A) {
                    if (A[E].children) {
                        var M = A[E].children[0].numHTML;
                        var B = new Date(L, H, M);
                        var i = new Date(N.substring(0, 4), N.substring(5, 7) - 1, N.substring(8, 10));
                        if (B.getTime() == i.getTime()) {
                            A[E].style.border = "1px solid #a5b9da";
                            A[E].style.background = k
                        } else {
                            A[E].style.border = "";
                            A[E].style.background = ""
                        }
                    }
                }
            }
        }

        document.onclick = function (i) {
            if (!d) {
                g.hide();
                ar.closeView()
            }
        };
        function ah() {
            b[4].style.display = "none";
            d = true;
            var B = document.body.clientWidth - 522 - 10;
            var E = f(y).offset().top;
            var D = f(y).offset().left;
            D = D >= B ? B : D;
            var A = f(y).innerHeight();
            g.css("left", D);
            g.css("top", E + A);
            var i = an(f(y).val());
            if (i && i.length > 4 && i.substring(0, 4) > 1900 && i.substring(0, 4) < 2051) {
                at(i.substring(0, 4), i.substring(5, 7) - 1, i.substring(0, 4), i.substring(5, 7))
            }
            af();
            g.show()
        }

        f(y).mouseout(function () {
            d = false
        });
        f(y).mouseover(function () {
            d = true
        });
        g.mouseover(function () {
            f(y).unbind("blur")
        });
        g.click(function (i) {
            i.stopPropagation();
            d = false
        });
        g.mouseout(function () {
            f(y).bind("blur", function () {
                g.hide()
            })
        });
        b[14].onclick = function () {
            if (!m()) {
                var D = "-";
                var A = new Date();
                var E = A.getFullYear();
                var L = A.getMonth() + 1;
                if (L >= 1 && L <= 9) {
                    L = "0" + L
                }
                var B = A.getDate();
                if (B >= 0 && B <= 9) {
                    B = "0" + B
                }
                var i = E + D + L + D + B;
                var H = I ? i : i + " " + z[A.getDay()];
                H = ac ? ac + H : H;
                H = s ? H + s : H;
                f(y).val(H);
                f(y).change();
                g.hide()
            }
        };
        function aB(i) {
            if (i == "一月") {
                return 1
            }
            if (i == "二月") {
                return 2
            }
            if (i == "三月") {
                return 3
            }
            if (i == "四月") {
                return 4
            }
            if (i == "五月") {
                return 5
            }
            if (i == "六月") {
                return 6
            }
            if (i == "七月") {
                return 7
            }
            if (i == "八月") {
                return 8
            }
            if (i == "九月") {
                return 9
            }
            if (i == "十月") {
                return 10
            }
            if (i == "十一月") {
                return 11
            }
            if (i == "十二月") {
                return 12
            }
            return i
        }

        a[0].onclick = function () {
            var B = e[1].value;
            var i = aB(e[0].value);
            var D = new Date(B - 1, i, 1);
            var E = new Date(D.getTime() - 1000 * 60 * 60 * 24);
            var A = new Date(E.getFullYear(), Number(E.getMonth()), E.getDate());
            if (A >= new Date(F)) {
                at(B - 1, i - 1, B - 1, i)
            } else {
                var D = new Date(F);
                at(D.getFullYear(), D.getMonth(), D.getFullYear(), D.getMonth() + 1)
            }
            af()
        };
        a[1].onclick = function () {
            var B = e[1].value;
            var i = aB(e[0].value);
            var D = new Date(B, i - 1, 1);
            var E = new Date(D.getTime() - 1000 * 60 * 60 * 24);
            var A = new Date(E.getFullYear(), Number(E.getMonth()), E.getDate());
            if (A >= new Date(F)) {
                at(B, i - 2, B, i - 1)
            }
            af()
        };
        a[6].onclick = function () {
            var A = e[1].value;
            var i = aB(e[0].value);
            if (!(A == ai.substring(0, 4) && i == ai.substring(5, 7))) {
                at(A, i, A, Number(i) + 1)
            }
            af()
        };
        a[5].onclick = function () {
            var A = e[1].value;
            var i = aB(e[0].value);
            if (A < ai.substring(0, 4)) {
                at(Number(A) + 1, i - 1, Number(A) + 1, i)
            } else {
                var B = new Date(ai);
                at(B.getFullYear(), B.getMonth(), B.getFullYear(), B.getMonth() + 1)
            }
            af()
        };
        a[8].onclick = function () {
            var A = e[2].value;
            var i = aB(e[3].value);
            var B = new Date(A, i - 1, 1);
            if (B <= new Date(ai)) {
                at(A, i - 1, A, i)
            }
            af()
        };
        a[7].onclick = function () {
            var A = e[1].value;
            var i = aB(e[0].value);
            if (A < ai.substring(0, 4)) {
                at(Number(A) + 1, i - 1, Number(A) + 1, i)
            } else {
                var B = new Date(ai);
                at(B.getFullYear(), B.getMonth(), B.getFullYear(), B.getMonth() + 1)
            }
            af()
        };
        function j(H) {
            var L = h[H].children;
            for (var i in L) {
                if (L[i].innerHTML) {
                    var E = H == 0 ? e[1].value : e[2].value;
                    var B = aB(L[i].innerHTML);
                    var D = F.substring(0, 4);
                    var M = Number(F.substring(5, 7));
                    var N = ai.substring(0, 4);
                    var A = Number(ai.substring(5, 7));
                    if ((E < D || E > N) || (E == D && B < M) || (E == N && B > A)) {
                        L[i].style.color = ao;
                        L[i].style.cursor = "auto"
                    } else {
                        L[i].style.color = aj;
                        L[i].style.cursor = "pointer"
                    }
                }
            }
        }

        function av(B, A) {
            h[B].innerHTML = "";
            var L = "";
            for (var M = A - 5; M <= A + 4; M++) {
                if (M < F.substring(0, 4) || M > ai.substring(0, 4)) {
                    L += '<li style="color: ' + ao + ';cursor:auto;">' + M + "</li>"
                } else {
                    L += '<li style="color: ' + aj + ';cursor:pointer;">' + M + "</li>"
                }
            }
            h[B].innerHTML = L;
            var D = B == 1 ? b[5] : b[11];
            if (Number(h[B].children[0].innerHTML) - 1 < F.substring(0, 4)) {
                D.children[0].style.color = ao;
                D.children[0].style.cursor = "auto"
            } else {
                D.children[0].style.color = aj;
                D.children[0].style.cursor = "pointer"
            }
            if (Number(h[B].children[9].innerHTML) + 1 > ai.substring(0, 4)) {
                D.children[1].style.color = ao;
                D.children[1].style.cursor = "auto"
            } else {
                D.children[1].style.color = aj;
                D.children[1].style.cursor = "pointer"
            }
            if (B == 3) {
                var H = h[3].parentElement.getElementsByTagName("li")
            } else {
                if (B == 1) {
                    var H = b[4].getElementsByTagName("li")
                }
            }
            for (var E = 0; E < H.length; E++) {
                if (!(H[E].innerHTML < F.substring(0, 4) || H[E].innerHTML > ai.substring(0, 4))) {
                    H[E].onclick = function () {
                        var N = this.innerHTML;
                        var i = B == 3 ? aB(e[3].value) + "" : aB(e[0].value) + "";
                        i = i.length == 1 ? "0" + i : i;
                        if (B == 3) {
                            at(N, i - 2, N, i - 1);
                            h[3].parentElement.style.display = "none"
                        } else {
                            if (B == 1) {
                                at(N, i - 1, N, i);
                                b[4].style.display = "none"
                            }
                        }
                        af()
                    }
                } else {
                    H[E].onclick = function () {
                        b[4].style.display = "none";
                        b[10].style.display = "none"
                    }
                }
            }
        }

        var aa = new Array("", "一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月", "一月");
        var z = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
        var t = navigator.userAgent.indexOf("MSIE") != -1 && !window.opera;
        var w = [19416, 19168, 42352, 21717, 53856, 55632, 91476, 22176, 39632, 21970, 19168, 42422, 42192, 53840, 119381, 46400, 54944, 44450, 38320, 84343, 18800, 42160, 46261, 27216, 27968, 109396, 11104, 38256, 21234, 18800, 25958, 54432, 59984, 28309, 23248, 11104, 100067, 37600, 116951, 51536, 54432, 120998, 46416, 22176, 107956, 9680, 37584, 53938, 43344, 46423, 27808, 46416, 86869, 19872, 42448, 83315, 21200, 43432, 59728, 27296, 44710, 43856, 19296, 43748, 42352, 21088, 62051, 55632, 23383, 22176, 38608, 19925, 19152, 42192, 54484, 53840, 54616, 46400, 46496, 103846, 38320, 18864, 43380, 42160, 45690, 27216, 27968, 44870, 43872, 38256, 19189, 18800, 25776, 29859, 59984, 27480, 21952, 43872, 38613, 37600, 51552, 55636, 54432, 55888, 30034, 22176, 43959, 9680, 37584, 51893, 43344, 46240, 47780, 44368, 21977, 19360, 42416, 86390, 21168, 43312, 31060, 27296, 44368, 23378, 19296, 42726, 42208, 53856, 60005, 54576, 23200, 30371, 38608, 19415, 19152, 42192, 118966, 53840, 54560, 56645, 46496, 22224, 21938, 18864, 42359, 42160, 43600, 111189, 27936, 44448];
        var G = ["小寒", "大寒", "立春", "雨水", "惊蛰", "春分", "清明", "谷雨", "立夏", "小满", "芒种", "夏至", "小暑", "大暑", "立秋", "处暑", "白露", "秋分", "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"];
        var X = [0, 21208, 43467, 63836, 85337, 107014, 128867, 150921, 173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033, 353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758];
        var Z = "日一二三四五六七八九十";
        var J = ["正", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "腊"];
        var W = "初十廿卅";
        var n = {"0101": "*1元旦", "0501": "*1劳动", "1001": "*7国庆"};
        var r = {"0101": "*6春节", "0115": "*1元宵", "0505": "*1端午", "0815": "*1中秋", "0100": "除夕"};

        function q(B) {
            function N(R, S) {
                var T = new Date((31556925974.7 * (R - 1900) + X[S] * 60000) + Date.UTC(1900, 0, 6, 2, 5));
                return (T.getUTCDate())
            }

            function M(R) {
                var T, S = 348;
                for (T = 32768; T > 8; T >>= 1) {
                    S += (w[R - 1900] & T) ? 1 : 0
                }
                return (S + P(R))
            }

            function P(R) {
                if (E(R)) {
                    return ((w[R - 1900] & 65536) ? 30 : 29)
                } else {
                    return (0)
                }
            }

            function E(R) {
                return (w[R - 1900] & 15)
            }

            function L(R, S) {
                return ((w[R - 1900] & (65536 >> S)) ? 30 : 29)
            }

            function A(R) {
                var T, U = 0, V = 0;
                var S = new Date(1900, 0, 31);
                var aC = (R - S) / 86400000;
                this.dayCyl = aC + 40;
                this.monCyl = 14;
                for (T = 1900; T < 2050 && aC > 0; T++) {
                    V = M(T);
                    aC -= V;
                    this.monCyl += 12
                }
                if (aC < 0) {
                    aC += V;
                    T--;
                    this.monCyl -= 12
                }
                this.year = T;
                this.yearCyl = T - 1864;
                U = E(T);
                this.isLeap = false;
                for (T = 1; T < 13 && aC > 0; T++) {
                    if (U > 0 && T == (U + 1) && this.isLeap == false) {
                        --T;
                        this.isLeap = true;
                        V = P(this.year)
                    } else {
                        V = L(this.year, T)
                    }
                    if (this.isLeap == true && T == (U + 1)) {
                        this.isLeap = false
                    }
                    aC -= V;
                    if (this.isLeap == false) {
                        this.monCyl++
                    }
                }
                if (aC == 0 && U > 0 && T == U + 1) {
                    if (this.isLeap) {
                        this.isLeap = false
                    } else {
                        this.isLeap = true;
                        --T;
                        --this.monCyl
                    }
                }
                if (aC < 0) {
                    aC += V;
                    --T;
                    --this.monCyl
                }
                this.month = T;
                this.day = aC + 1
            }

            function Q(R) {
                return R < 10 ? "0" + R : R
            }

            function H(S, R) {
                var T = S;
                return R.replace(/dd?d?d?|MM?M?M?|yy?y?y?/g, function (V) {
                    switch (V) {
                        case"yyyy":
                            var U = "000" + T.getFullYear();
                            return U.substring(U.length - 4);
                        case"dd":
                            return Q(T.getDate());
                        case"d":
                            return T.getDate().toString().length == 1 ? "0" + T.getDate().toString() : T.getDate().toString();
                        case"MM":
                            return Q((T.getMonth() + 1));
                        case"M":
                            return (T.getMonth() + 1).toString().length == 1 ? "0" + (T.getMonth() + 1).toString() : (T.getMonth() + 1).toString()
                    }
                })
            }

            function i(S, T) {
                var R;
                switch (S, T) {
                    case 10:
                        R = "初十";
                        break;
                    case 20:
                        R = "二十";
                        break;
                    case 30:
                        R = "三十";
                        break;
                    default:
                        R = W.charAt(Math.floor(T / 10));
                        R += Z.charAt(T % 10)
                }
                return (R)
            }

            this.date = B;
            this.isToday = false;
            this.isRestDay = false;
            this.solarYear = H(B, "yyyy");
            this.solarMonth = H(B, "MM");
            this.solarDate = H(B, "dd");
            this.calendarDate = new Date(this.solarYear, this.solarMonth - 1, this.solarDate);
            this.solarWeekDay = B.getDay();
            this.solarWeekDayInChinese = "星期" + Z.charAt(this.solarWeekDay);
            var D = new A(B);
            this.lunarYear = D.year;
            this.lunarMonth = D.month;
            this.lunarIsLeapMonth = D.isLeap;
            this.lunarMonthInChinese = this.lunarIsLeapMonth ? "闰" + J[D.month - 1] : J[D.month - 1];
            this.lunarDate = D.day;
            this.showInLunar = this.lunarDateInChinese = i(this.lunarMonth, this.lunarDate);
            if (this.lunarDate == 1) {
                this.showInLunar = this.lunarMonthInChinese + "月"
            }
            this.jieqi = "";
            this.restDays = 0;
            if (N(this.solarYear, (this.solarMonth - 1) * 2) == H(B, "d")) {
                this.showInLunar = this.jieqi = G[(this.solarMonth - 1) * 2]
            }
            if (N(this.solarYear, (this.solarMonth - 1) * 2 + 1) == H(B, "d")) {
                this.showInLunar = this.jieqi = G[(this.solarMonth - 1) * 2 + 1]
            }
            if (this.showInLunar == "清明") {
                this.showInLunar = "清明";
                this.restDays = 1
            }
            this.solarFestival = n[H(B, "MM") + H(B, "dd")];
            if (typeof this.solarFestival == "undefined") {
                this.solarFestival = ""
            } else {
                if (/\*(\d)/.test(this.solarFestival)) {
                    this.restDays = parseInt(RegExp.$1);
                    this.solarFestival = this.solarFestival.replace(/\*\d/, "")
                }
            }
            this.showInLunar = (this.solarFestival == "") ? this.showInLunar : this.solarFestival;
            this.lunarFestival = r[this.lunarIsLeapMonth ? "00" : Q(this.lunarMonth) + Q(this.lunarDate)];
            if (typeof this.lunarFestival == "undefined") {
                this.lunarFestival = ""
            } else {
                if (/\*(\d)/.test(this.lunarFestival)) {
                    this.restDays = (this.restDays > parseInt(RegExp.$1)) ? this.restDays : parseInt(RegExp.$1);
                    this.lunarFestival = this.lunarFestival.replace(/\*\d/, "")
                }
            }
            if (this.lunarMonth == 12 && this.lunarDate == L(this.lunarYear, 12)) {
                this.lunarFestival = r["0100"];
                this.restDays = 1
            }
            this.showInLunar = (this.lunarFestival == "") ? this.showInLunar : this.lunarFestival;
            this.showInLunar = (this.showInLunar.length > 4) ? this.showInLunar.substr(0, 2) + "..." : this.showInLunar;
            if (this.showInLunar == "清明") {
                this.solarFestival = "清明"
            }
        }

        var v = (function () {
            var E = {};
            E.lines = 0;
            E.dateArray = new Array(42);
            function D(H) {
                return (((H % 4 === 0) && (H % 100 !== 0)) || (H % 400 === 0))
            }

            function i(L, H) {
                return [31, (D(L) ? 29 : 28), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31][H]
            }

            function B(L, H) {
                L.setDate(L.getDate() + H);
                return L
            }

            function A(V, U) {
                var aD = V.solarMonth - 2;
                if (V.solarMonth == 0) {
                    aD = 11
                } else {
                    if (V.solarMonth == 1) {
                        aD = 10
                    }
                }
                var S = new q(new Date(V.solarYear, aD, 1));
                var Q = S.solarWeekDay;
                var L = new q(new Date(V.solarYear, V.solarMonth, 1));
                var H = L.solarWeekDay;
                var N = 0;
                var T = new q(new Date(V.solarYear, V.solarMonth - 1, 1));
                var R = T.solarWeekDay;
                if (al) {
                    E.lines = Math.ceil((R + i(V.solarYear, V.solarMonth - 1)) / 7)
                } else {
                    if (U == 0) {
                        var M = Math.ceil((R + i(V.solarYear, V.solarMonth - 1)) / 7);
                        var aC = Math.ceil((H + i(V.solarYear, Number(V.solarMonth) == 12 ? 0 : Number(V.solarMonth))) / 7);
                        E.lines = M > aC ? M : aC
                    } else {
                        if (U == 1) {
                            var M = Math.ceil((R + i(V.solarYear, V.solarMonth - 1)) / 7);
                            var aC = Math.ceil((Q + i(V.solarYear, aD)) / 7);
                            E.lines = M > aC ? M : aC
                        } else {
                            E.lines = 6
                        }
                    }
                }
                for (var P = 0; P < E.dateArray.length; P++) {
                    if (T.restDays != 0) {
                        N = T.restDays
                    }
                    if (N > 0) {
                        T.isRest = true
                    }
                    if (R-- > 0 || T.solarMonth != V.solarMonth) {
                        E.dateArray[P] = null;
                        continue
                    }
                    var U = new q(new Date());
                    if (T.solarYear == U.solarYear && T.solarMonth == U.solarMonth && T.solarDate == U.solarDate) {
                        T.isToday = true
                    }
                    E.dateArray[P] = T;
                    T = new q(B(T.date, 1));
                    N--
                }
            }

            return {
                init: function (L, H) {
                    A(L, H)
                }, getJson: function () {
                    return E
                }
            }
        })();
        var x = (function () {
            function i(H) {
                var E = H == 1 ? b[6] : b[13];
                var A = v.getJson();
                var M = A.dateArray;
                var L = ap == "2" ? 38 : 22;
                E.style.height = A.lines * L + 2 + "px";
                E.innerHTML = "";
                for (var Q = 0; Q < M.length; Q++) {
                    if (M[Q] == null) {
                        continue
                    }
                    var R = M[Q].solarYear + "-" + M[Q].solarMonth + "-" + M[Q].solarDate;
                    var P = I ? R : R + " " + M[Q].solarWeekDayInChinese;
                    P = ac ? ac + P : P;
                    P = s ? P + s : P;
                    var D = u("DIV");
                    if (M[Q].isToday) {
                        D.style.border = "1px solid #a5b9da";
                        D.style.background = k
                    }
                    D.className = "cell";
                    if (ap == "2") {
                        D.style.height = "36px"
                    }
                    D.style.left = Q % 7 == 0 ? "0px" : (Q % 7) * 36 + 3 + "px";
                    D.style.top = Math.floor(Q / 7) * L + 5 + "px";
                    if (M[Q].calendarDate >= new Date(F) && M[Q].calendarDate <= new Date(ai)) {
                        D.onclick = (function (S) {
                            return function () {
                                f(y).val(S);
                                g.hide();
                                f(y).change()
                            }
                        })(P);
                        D.style.cursor = "pointer"
                    }
                    var N = u("DIV");
                    N.className = "so";
                    N.style.color = ((Q % 7) == 0 || (Q % 7) == 6 || M[Q].isRest || M[Q].isToday) ? az : ae;
                    if (!(M[Q].calendarDate >= new Date(F) && M[Q].calendarDate <= new Date(ai))) {
                        N.style.color = ao
                    }
                    if (ap == "3") {
                        if (M[Q].solarFestival) {
                            N.innerHTML = M[Q].solarFestival
                        } else {
                            if (M[Q].lunarFestival) {
                                N.innerHTML = M[Q].lunarFestival
                            } else {
                                if (M[Q].isToday) {
                                    N.innerHTML = "今天"
                                } else {
                                    N.innerHTML = M[Q].solarDate.substring(0, 1) == "0" ? M[Q].solarDate.substring(1) : M[Q].solarDate
                                }
                            }
                        }
                        N.numHTML = M[Q].solarDate.substring(0, 1) == "0" ? M[Q].solarDate.substring(1) : M[Q].solarDate
                    } else {
                        N.innerHTML = M[Q].solarDate.substring(0, 1) == "0" ? M[Q].solarDate.substring(1) : M[Q].solarDate;
                        N.numHTML = M[Q].solarDate.substring(0, 1) == "0" ? M[Q].solarDate.substring(1) : M[Q].solarDate
                    }
                    D.appendChild(N);
                    if (ap == "2") {
                        var B = u("DIV");
                        if (!(M[Q].calendarDate >= new Date(F) && M[Q].calendarDate <= new Date(ai))) {
                            B.style.color = ao
                        } else {
                            B.style.color = K
                        }
                        B.innerHTML = M[Q].showInLunar;
                        D.appendChild(B)
                    }
                    E.appendChild(D)
                }
            }

            return {
                draw: function (A) {
                    if (A == 0) {
                        i(A)
                    } else {
                        if (A == 1) {
                            i(1)
                        } else {
                            i(A);
                            i(1)
                        }
                    }
                }, resetYM: function (A, B) {
                    e[0].value = aa[Number(A.solarMonth)];
                    e[1].value = A.solarYear;
                    e[2].value = B.solarYear;
                    e[3].value = aa[Number(B.solarMonth)]
                }
            }
        })();
        var ab = new q(new Date());
        v.init(ab, 0);
        x.draw(1);
        if (!al) {
            a[6].style.display = "none";
            a[5].style.display = "none";
            var aq = new Date();
            var p = new q(new Date(aq.getFullYear(), aq.getMonth() + 1, aq.getDate()));
            v.init(p, 1);
            x.draw(0)
        } else {
            g[0].className = "cal-wrap cal-one"
        }
        if (!l) {
            b[14].style.display = "none"
        }
        if (aA) {
            a[2].onclick = function () {
                if (h[1].getElementsByTagName("li")[0].innerHTML < 1902 || this.style.cursor == "auto") {
                    b[4].style.display = "none";
                    return
                }
                av(1, h[1].getElementsByTagName("li")[0].innerHTML - 5)
            };
            a[3].onclick = function () {
                if (h[1].getElementsByTagName("li")[0].innerHTML > 2040 || this.style.cursor == "auto") {
                    b[4].style.display = "none";
                    return
                }
                av(1, Number(h[1].getElementsByTagName("li")[0].innerHTML) + 15)
            };
            a[4].onclick = function () {
                h[1].parentElement.style.display = "none"
            };
            a[9].onclick = function () {
                if (h[3].getElementsByTagName("li")[0].innerHTML < 1902 || this.style.cursor == "auto") {
                    b[10].style.display = "none";
                    return
                }
                av(3, h[3].getElementsByTagName("li")[0].innerHTML - 5)
            };
            a[10].onclick = function () {
                if (h[3].getElementsByTagName("li")[0].innerHTML > 2040 || this.style.cursor == "auto") {
                    b[10].style.display = "none";
                    return
                }
                av(3, Number(h[3].getElementsByTagName("li")[0].innerHTML) + 15)
            };
            a[11].onclick = function () {
                h[3].parentElement.style.display = "none"
            };
            e[0].onfocus = function () {
                h[0].style.display = "block";
                j(0);
                b[4].style.display = "none"
            };
            e[0].onblur = function () {
                h[0].style.display = "none"
            };
            var ad = h[0].getElementsByTagName("li");
            for (var ax = 0; ax < ad.length; ax++) {
                ad[ax].onclick = function () {
                    if (this.style.cursor == "auto") {
                        h[0].style.display = "none";
                        h[4].style.display = "none";
                        return
                    }
                    var A = e[1].value;
                    var i = aB(this.innerHTML) + "";
                    i = i.length == 1 ? "0" + i : i;
                    at(A, i - 1, A, i);
                    h[0].style.display = "none";
                    af()
                }
            }
            j(0);
            e[1].onfocus = function () {
                av(1, Number(e[1].value));
                b[4].style.display = "block"
            };
            e[1].onblur = function () {
                b[4].style.display = "none"
            };
            b[4].onmouseover = function () {
                e[1].onblur = function () {
                }
            };
            b[4].onmouseout = function () {
                e[1].onblur = function () {
                    b[4].style.display = "none"
                }
            };
            h[0].onmouseover = function () {
                e[0].onblur = function () {
                }
            };
            h[0].onmouseout = function () {
                e[0].onblur = function () {
                    h[0].style.display = "none"
                }
            };
            e[3].onfocus = function () {
                j(4);
                h[4].style.display = "block";
                b[10].style.display = "none"
            };
            e[3].onblur = function () {
                h[4].style.display = "none"
            };
            var O = h[4].getElementsByTagName("li");
            for (var ax = 0; ax < O.length; ax++) {
                O[ax].onclick = function () {
                    if (this.style.cursor == "auto") {
                        h[0].style.display = "none";
                        h[4].style.display = "none";
                        return
                    }
                    var A = e[2].value;
                    var i = aB(this.innerHTML) + "";
                    i = i.length == 1 ? "0" + i : i;
                    at(A, i - 2, A, i - 1);
                    h[4].style.display = "none";
                    af()
                }
            }
            j(4);
            e[2].onfocus = function () {
                av(3, Number(e[2].value));
                b[10].style.display = "block"
            };
            e[2].onblur = function () {
                b[10].style.display = "none"
            };
            b[10].onmouseover = function () {
                e[2].onblur = function () {
                }
            };
            b[10].onmouseout = function () {
                e[2].onblur = function () {
                    b[10].style.display = "none"
                }
            };
            h[4].onmouseover = function () {
                e[3].onblur = function () {
                }
            };
            h[4].onmouseout = function () {
                e[3].onblur = function () {
                    h[4].style.display = "none"
                }
            };
            for (var ax = 0; ax < 4; ax++) {
                e[ax].disabled = false;
                e[ax].style.cursor = "pointer"
            }
        }
        var au = new Date();
        e[0].value = aa[au.getMonth() + 1];
        e[1].value = au.getFullYear();
        e[2].value = au.getMonth() == 11 ? au.getFullYear() + 1 : au.getFullYear();
        e[3].value = aa[au.getMonth() + 2];
        ah()
    };
    f.jcalendar.defaultOptions = {
        isSingle: true,
        showFormat: true,
        formatBeforeInfo: "",
        formatAfterInfo: "",
        startDate: "1901-01-01",
        endDate: "2050-12-31",
        isTwoRows: "3",
        isTodayBlock: true,
        isYearMonthDisabled: true,
        condition: [false, "#query_H", "active", "2050-12-31"],
        restColor: "#c60b02",
        noRestColor: "#313131",
        todayClickColor: "#c1d9ff",
        noClickColor: "#aaa",
        clickByYearMonth: "#003784",
        lunarColor: "#666",
        closeCalendar: function () {
        },
        onpicked: function () {
        }
    };
    f.fn.jcalendar = function () {
        var i = Array.prototype.slice.call(arguments);
        return (new f.jcalendar(this, i[0]))
    }
})(jQuery);
