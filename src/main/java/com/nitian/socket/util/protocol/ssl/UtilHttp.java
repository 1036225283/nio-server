package com.nitian.socket.util.protocol.ssl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


import java.net.URL;

/**
 * Created by xws on 12/24/17.
 */
public class UtilHttp {

    private String sessionId = null;
    private static String strCookie;
    private StringBuffer param = new StringBuffer();
    private String strHost;


    public static void main(String[] args) throws Exception {
        // String
        // url="data=4iumB+SsF1V+MNICp0AlOg==-1|f/UMZfwXelfPlnqdE9p2cA==-1|4qmHvoFlcYhbxRSwszuRZA==-1|oWlptcPWYxQI/Eo7qlElgA==-1|KVClyIQ6qOkNjNW7zqoVCw==-1|vtNYWESvf+dYBbw7unikSQ==-1|oiSYjgCHape2K46p1OG58g==-1|S2nJt6jPwcRrjRsZUWGGTQ==-1|d5ts0O3Kc94cDH1CSg82eA==-1|RjM1LXPOuqDY4nUZwhDMXA==-1|5OQa5d074p41SwltPb6fmw==-1|YiswVyAGQDVZGraJHFSCmQ==-1|VNRyM0ff4UK8WuhkIOFbuQ==-1|BALUi2UkCbq+ZzOwUtyNBg==-1|w+g0Lr6koWrEfl931OO54A==-1|NlwMj1EnLuaOnakkSlU5YA==-1|U9hWY1ojWjOG3OGT/VTuuQ==-1|flLFh5fl7PwsroQUEoEnag==-1|bl1PM31Ztpw8xL7lDo0RWg==-1|wirgAmWWfDG3o7vgXZsHpg==-1|cr4Lgj3tQtevbQX7J4iVLA==-1|1kTRC5a8SXzRFrOFZyDDaA==-1|Y/KdJUUhZunMIukWyyoJhg==-1|oJrEA0B46UTx6S4jbzf+1A==-1|ROy6knUlQEw5pax33OliVA==-1|be3QPNge8TjJzuZpZVj23Q==-1|EZDLnKSOzpfMabjPxp1W4g==-1|muT/t8DOTo2Sl19B8/FCGg==-1|k258tCRhQF5YG4BiWJM1Zg==-1|F20DWT4aGoFW9ExExmF6OA==-1|/i9e+56KCkbOm8IRddvPww==-1|HnTkWigicawuic61nxT5iw==-1|lm9/jpl+9J/15jOgQGrxpQ==-1|vEOJHcMbfgKb//84qUV/zA==-1|wdx5zkSVmdnt5xiilxdBmw==-1|ckAOxNBusOJZ7yfcWRCZuA==-1|caoeGG609qZy9pr8FMaZrA==-1|QD7L6h1uI0thGVfk7BAHZg==-1|ruwklTy8nafLCEsQEewr5A==-1|V7l/tXQ+1ynUiNXNKr5Z0A==-1|ORwuXco6Vknw11jck+8i1A==-1|STU1e0R6K5iuG0QCYPBrDQ==-1|7Xmb5cXp0QuOnakkSlU5YA==-1|hXQMTHJUBCkQQ+hIZLuPig==-1|t02TVC8DD642W8NQATX5Tg==-1|LAhS9QdrTReG3OGT/VTuuQ==-1|ufR/4cOnJ7iaf8VOCT7IfQ==-1|70qfTVvAlXKfeUTZo+pPGw==-1|R9PYhB6AdspwxrJ5UhJa8w==-1|brKHXJBt1ecX375oOhhgtw==-1|CYp5hmRCRgaFGoX86/QqzA==-1|HdLAeoJqYI7E3i/S65CktA==-1|AIErdIHH4bmzoXXffE3bSA==-1|e66U2xGdgJEzdtIHCgCsjg==-1|p674wgv3W/pi4NWBrz0iAQ==-1|ACPh2iFYEV3OmVeKf5w2sQ==-1|fKATin6yQalA3LxtkV6pdw==-1|C1GymtlZCB6R/EywELb5sA==-1|bEJRutBiG42WTUu//w8BVA==-1|+ePml1nf59eEV1peYtUfKQ==-1|z3AIaBOscBlrjRsZUWGGTQ==-1|2dOaq3wYVjP3syp1zQ8eng==-1|br/zstW2+tD4ezyWVlgfLg==-1|WmTsI1TWmNptO2eyJu33CQ==-1|pz+fSOiP+arowmRFb3jNuA==-1|zR9XU807zrqdl9M4BLiojg==-1|78RUqmJDbe/hUXv/XBVOSA==-1|L6hcWqVxRlFNlYYQsHtmDA==-1|ti7LQ2FEPbHgWjXH2/ppYw==-1|yO0DmD7F4W3jhPtv9esj2g==-1|jmfLSaYBe54fgerog9K+dQ==-1|S0Nek2jtsb5CCVisse1P9g==-1|QMQLsrYK+/qBeMvuiD57DA==-1|sF1CjEhIgJ/KaqO+UodWqg==-1|bngsZXz3Ubn23pnkcuNXkQ==-1|k31XMZ1m8jq3LtXyJK/5lQ==-1|kur2FtP4jxtAKkB3CmojCQ==-1|6oGTPJgewW2xWab46YAOLg==-1|+N7gWnSQzXeiE60jDjz+FA==-1|drDzGfozccB0xl4z2iFIAg==-1|MyJEXYFJxq1BBY+PGVwtog==-1|LZVWVBu2tjthG835A9i3tQ==-1|v9B5Zs8D3h/PlnqdE9p2cA==-1|gePvq1Gj6Tb7+KEeZ6SMSA==-1|7p77L24ipmyiE60jDjz+FA==-1|jCiyudIy9q2b6Vo//pcpDg==-1|ByfS7WuwKWSBeMvuiD57DA==-1|ISpViFLUG7aiKP7LF4+WSQ==-1|NSE2Drg51mULJQaqW9t7/Q==-1|owfI6e8D/oDPEsUwXp5AJg==-1|6KXXYB9o3wAvswSaA+8gjA==-1|pSFmEXCKGwWV0Iz5157sew==-1|TEVaSKsPHjIYvwxzBJzVZA==-1|6WWnYSkh9DbW3a1QXEwmjw==-1|uwkF8oYnpF7i0lG5RJZVBw==-1|gjILWjY1nR6JwVl46ZHVJw==-1|M93g+D3cMqRuXeYKCfJsXQ==-1|/u5COgC3xOmCRLDn18rR/Q==-1|nMQ94PqM9xNcQRs7Mh/jNA==-1|t8ZHvXfm/bX0ELpiLXgZMw==-1";

        // url =
        // "data=h9OJUibH9KmBwpp59jek5A==-1|MJ8IIWQOWZiNMn1kFufrQg==-1|VsbnHjtmX0ooBwYgXRfF5Q==-1|nK4rIPAHeG8spX7rjKNM9Q==-1|VRNrpKKYB9WATPRXEaouqw==-1|G2Hc4L2IF7YFlAjjoq8nkg==-1|ME1SXwnPVoNKSbbapB/OGw==-1|hcxXYzFwULsOLqalB8U4cg==-1|APo2bWPJscfhS6BQOfFQ/Q==-1|4jnmdeB/oolAKkB3CmojCQ==-1|Lm+m9Kz4ZXpG5Hc53joEEQ==-1|+kzqCrBk5YOmvumk0S+HhQ==-1|b+fCj2ZxqkczDhX1NrTXNA==-1|QJCO30EncxXOmVeKf5w2sQ==-1|+NdTvv7rQcBZ62EvjKQsPg==-1|nu4Zs0lzuGSp5cu9MFnqWg==-1|2dJ+h/mnb2edbR6KBVTIQQ==-1|N/rwEP93mon4/2PCxZmp0g==-1|3U3aDtMCg2MxYB4bBz02aA==-1|kK5P/iMAilLz9+hiDmPDvw==-1|03+jn7QOrz8u5x3WRLFSPQ==-1|DQK851rBJcsmqcLTxxxRJw==-1|3USziGeZTPKmOT5jioKynA==-1|zXQOAmSZyneGvq1c3YOrIQ==-1|x/VwsfOAUc8/Bx0Y11iPuA==-1|11jjIS5g6BK4rJlRn+I6CA==-1|q0dXzvzX1mvM6rGQAbxL6A==-1|U9KTK+/4NIArdW+e712x1A==-1|jF5t0BSTPaKsdYBbdJ3qEA==-1|/5YfjgIX9OSj9ep1j4bs/A==-1|KsuO572A7tkvswSaA+8gjA==-1|qUB5X2inq7k/7G5chh+L5g==-1|/w+dMPmVTmaGWUxZN5gr6A==-1|+Smyv4oTviMacva2d0DZaA==-1|FWleOO/uaAqx4bqwhedCeQ==-1|ZfUNCx7t91rt9DjMMGDdYQ==-1|UDmwGyi+4ozMsinxM1Wj3Q==-1|tpZVrtifDhWdbR6KBVTIQQ==-1|r3wdgXaxeCj15jOgQGrxpQ==-1|v1ja792KkfDyQgbvpboLDQ==-1|dnn4sGEI7CTFHVIQFvz0NA==-1|jEdI82xcHasqokdFGKqdpg==-1|7AjxGvBQfhJNkVwqULVKAA==-1|GvyVp8LFrx5W9ExExmF6OA==-1|V3jj6u6HBWCfXSYmCwAvrw==-1|3Z21GjVsiuKmOT5jioKynA==-1|/l6AMimCrt9YG4BiWJM1Zg==-1|VcSs8z5uNjcFST36tFZTuA==-1|H74bToGBkYBdS1yTcIqgMQ==-1|gtlyPbIVT/yhl9KN6G66Lg==-1|6CqSfBhtKbP7USJUBoN/QA==-1|GnsgOEkRaByBVAslyYFWKg==-1|mmy5pv5764WOnakkSlU5YA==-1|cXOgOkPKyxKaECMFxzrdtg==-1|2MrT8x5ho+oOnQPZ+hdhUg==-1|NTguEeHv1fxA3LxtkV6pdw==-1|4qiLR08J3toAjTRWh1EVYg==-1|ybFki+WLVYBU0ZsUfTcTcg==-1|SsNqLQsINZdNq6WGjyDzEQ==-1|3gzw03zrpNCYHSFwM/hg/w==-1|oJ+5MUCwM0kFlAjjoq8nkg==-1|pnMC0IDXdS/+arJWCzrWJA==-1|zcTKnP0jdNH8MFvMe+wpIg==-1|6xey+xvfsECmrOSC1d8EDQ==-1|G0RGtoHcHHFfRkRCmcOipA==-1|qWonrYokXos6F2bRkSY0pw==-1|W30TSQpqAAxjJnQlbMJPrw==-1|dl92KCBhOf2ZT3byp22r7g==-1|eYsKtVSBVEV06RTnhiPTZg==-1|jBb2kTZdMA3PBzH5usduOg==-1|P8LRQmUN6n8AjTRWh1EVYg==-1|w44DemXbF4ax1oz/EE1Dfg==-1|3SxcwQHmYfzCB1G1NoSZRQ==-1|7blNgC9c5P9Ehxgvnlvzag==-1|AgQl61+/OoZmNW0wnrsWEA==-1|C7hWO2zQ0VgzER4oFDEk3w==-1|HwU9mOwpjIpZ62EvjKQsPg==-1|3yPWYkAokuohVMwza2Vk0w==-1|pdj/sQQ7hHDf2O7bZtQiQA==-1|qU5rc2HU+b5clKqY7WnUQA==-1|DHJydADWkjStg3TxwnymyQ==-1|o1T3Wf43IYEXpwMyLNeH3A==-1|QedLCxJLEaBG5Hc53joEEQ==-1|U1/4CtqBeA7XCoFytsycQg==-1|Jzl4tRPkzOg6tjGtZZ1eFg==-1|lCzqm3WvaM3PlnqdE9p2cA==-1|VY8bhnCbkM7x6S4jbzf+1A==-1|Fit3ojZb/ywrdW+e712x1A==-1|vnQXzg/jSyuaECMFxzrdtg==-1|2s+EPFuq4ps5pax33OliVA==-1|S4IPKejL0/wphjvLAs8L5A==-1|rkVtaqhWgY+R/EywELb5sA==-1|3ZGRQchrhbb8RQ/Lz4XW6g==-1|unjCh9CVwAgaArnta33BsQ==-1|L436+7PeWSikDQDKhLmazA==-1|ClG8RqE2o6MJsSj96+u3NQ==-1|UU+m5gKh8iEvswSaA+8gjA==-1|CAgmLLA29kkK56yCCmM0+g==-1|dFhqiEgBt9mSZ3aziyLEbA==-1|AzZTXRM3qAMTxQLOEigBWQ==-1";
        // url =
        // "data=blfWbgRolTawVqf/kopY5g==-1|MJ8IIWQOWZiNMn1kFufrQg==-1|VsbnHjtmX0ooBwYgXRfF5Q==-1|nK4rIPAHeG8spX7rjKNM9Q==-1|VRNrpKKYB9WATPRXEaouqw==-1|G2Hc4L2IF7YFlAjjoq8nkg==-1|ME1SXwnPVoNKSbbapB/OGw==-1|hcxXYzFwULsOLqalB8U4cg==-1|APo2bWPJscfhS6BQOfFQ/Q==-1|4jnmdeB/oolAKkB3CmojCQ==-1|Lm+m9Kz4ZXpG5Hc53joEEQ==-1|+kzqCrBk5YOmvumk0S+HhQ==-1|b+fCj2ZxqkczDhX1NrTXNA==-1|QJCO30EncxXOmVeKf5w2sQ==-1|+NdTvv7rQcBZ62EvjKQsPg==-1|nu4Zs0lzuGSp5cu9MFnqWg==-1|2dJ+h/mnb2edbR6KBVTIQQ==-1|N/rwEP93mon4/2PCxZmp0g==-1|3U3aDtMCg2MxYB4bBz02aA==-1|kK5P/iMAilLz9+hiDmPDvw==-1|03+jn7QOrz8u5x3WRLFSPQ==-1|DQK851rBJcsmqcLTxxxRJw==-1|3USziGeZTPKmOT5jioKynA==-1|zXQOAmSZyneGvq1c3YOrIQ==-1|x/VwsfOAUc8/Bx0Y11iPuA==-1|11jjIS5g6BK4rJlRn+I6CA==-1|q0dXzvzX1mvM6rGQAbxL6A==-1|U9KTK+/4NIArdW+e712x1A==-1|jF5t0BSTPaKsdYBbdJ3qEA==-1|/5YfjgIX9OSj9ep1j4bs/A==-1|KsuO572A7tkvswSaA+8gjA==-1|qUB5X2inq7k/7G5chh+L5g==-1|/w+dMPmVTmaGWUxZN5gr6A==-1|+Smyv4oTviMacva2d0DZaA==-1|FWleOO/uaAqx4bqwhedCeQ==-1|ZfUNCx7t91rt9DjMMGDdYQ==-1|UDmwGyi+4ozMsinxM1Wj3Q==-1|tpZVrtifDhWdbR6KBVTIQQ==-1|r3wdgXaxeCj15jOgQGrxpQ==-1|v1ja792KkfDyQgbvpboLDQ==-1|dnn4sGEI7CTFHVIQFvz0NA==-1|jEdI82xcHasqokdFGKqdpg==-1|7AjxGvBQfhJNkVwqULVKAA==-1|GvyVp8LFrx5W9ExExmF6OA==-1|V3jj6u6HBWCfXSYmCwAvrw==-1|3Z21GjVsiuKmOT5jioKynA==-1|/l6AMimCrt9YG4BiWJM1Zg==-1|VcSs8z5uNjcFST36tFZTuA==-1|H74bToGBkYBdS1yTcIqgMQ==-1|gtlyPbIVT/yhl9KN6G66Lg==-1|6CqSfBhtKbP7USJUBoN/QA==-1|GnsgOEkRaByBVAslyYFWKg==-1|mmy5pv5764WOnakkSlU5YA==-1|cXOgOkPKyxKaECMFxzrdtg==-1|2MrT8x5ho+oOnQPZ+hdhUg==-1|NTguEeHv1fxA3LxtkV6pdw==-1|4qiLR08J3toAjTRWh1EVYg==-1|ybFki+WLVYBU0ZsUfTcTcg==-1|SsNqLQsINZdNq6WGjyDzEQ==-1|3gzw03zrpNCYHSFwM/hg/w==-1|oJ+5MUCwM0kFlAjjoq8nkg==-1|pnMC0IDXdS/+arJWCzrWJA==-1|zcTKnP0jdNH8MFvMe+wpIg==-1|6xey+xvfsECmrOSC1d8EDQ==-1|G0RGtoHcHHFfRkRCmcOipA==-1|qWonrYokXos6F2bRkSY0pw==-1|W30TSQpqAAxjJnQlbMJPrw==-1|dl92KCBhOf2ZT3byp22r7g==-1|eYsKtVSBVEV06RTnhiPTZg==-1|jBb2kTZdMA3PBzH5usduOg==-1|P8LRQmUN6n8AjTRWh1EVYg==-1|w44DemXbF4ax1oz/EE1Dfg==-1|3SxcwQHmYfzCB1G1NoSZRQ==-1|7blNgC9c5P9Ehxgvnlvzag==-1|AgQl61+/OoZmNW0wnrsWEA==-1|C7hWO2zQ0VgzER4oFDEk3w==-1|HwU9mOwpjIpZ62EvjKQsPg==-1|3yPWYkAokuohVMwza2Vk0w==-1|pdj/sQQ7hHDf2O7bZtQiQA==-1|qU5rc2HU+b5clKqY7WnUQA==-1|DHJydADWkjStg3TxwnymyQ==-1|o1T3Wf43IYEXpwMyLNeH3A==-1|QedLCxJLEaBG5Hc53joEEQ==-1|U1/4CtqBeA7XCoFytsycQg==-1|Jzl4tRPkzOg6tjGtZZ1eFg==-1|lCzqm3WvaM3PlnqdE9p2cA==-1|VY8bhnCbkM7x6S4jbzf+1A==-1|Fit3ojZb/ywrdW+e712x1A==-1|vnQXzg/jSyuaECMFxzrdtg==-1|2s+EPFuq4ps5pax33OliVA==-1|S4IPKejL0/wphjvLAs8L5A==-1|rkVtaqhWgY+R/EywELb5sA==-1|3ZGRQchrhbb8RQ/Lz4XW6g==-1|unjCh9CVwAgaArnta33BsQ==-1|L436+7PeWSikDQDKhLmazA==-1|ClG8RqE2o6MJsSj96+u3NQ==-1|UU+m5gKh8iEvswSaA+8gjA==-1|CAgmLLA29kkK56yCCmM0+g==-1|dFhqiEgBt9mSZ3aziyLEbA==-1|AzZTXRM3qAMTxQLOEigBWQ==-1";

        // url =
        // "4iumB+SsF1V+MNICp0AlOg==-1|f/UMZfwXelfPlnqdE9p2cA==-1|4qmHvoFlcYhbxRSwszuRZA==-1|oWlptcPWYxQI/Eo7qlElgA==-1|KVClyIQ6qOkNjNW7zqoVCw==-1|vtNYWESvf+dYBbw7unikSQ==-1|oiSYjgCHape2K46p1OG58g==-1|S2nJt6jPwcRrjRsZUWGGTQ==-1|d5ts0O3Kc94cDH1CSg82eA==-1|RjM1LXPOuqDY4nUZwhDMXA==-1|5OQa5d074p41SwltPb6fmw==-1|YiswVyAGQDVZGraJHFSCmQ==-1|VNRyM0ff4UK8WuhkIOFbuQ==-1|BALUi2UkCbq+ZzOwUtyNBg==-1|w+g0Lr6koWrEfl931OO54A==-1|NlwMj1EnLuaOnakkSlU5YA==-1|U9hWY1ojWjOG3OGT/VTuuQ==-1|flLFh5fl7PwsroQUEoEnag==-1|bl1PM31Ztpw8xL7lDo0RWg==-1|wirgAmWWfDG3o7vgXZsHpg==-1|cr4Lgj3tQtevbQX7J4iVLA==-1|1kTRC5a8SXzRFrOFZyDDaA==-1|Y/KdJUUhZunMIukWyyoJhg==-1|oJrEA0B46UTx6S4jbzf+1A==-1|ROy6knUlQEw5pax33OliVA==-1|be3QPNge8TjJzuZpZVj23Q==-1|EZDLnKSOzpfMabjPxp1W4g==-1|muT/t8DOTo2Sl19B8/FCGg==-1|k258tCRhQF5YG4BiWJM1Zg==-1|F20DWT4aGoFW9ExExmF6OA==-1|/i9e+56KCkbOm8IRddvPww==-1|HnTkWigicawuic61nxT5iw==-1|lm9/jpl+9J/15jOgQGrxpQ==-1|vEOJHcMbfgKb//84qUV/zA==-1|wdx5zkSVmdnt5xiilxdBmw==-1|ckAOxNBusOJZ7yfcWRCZuA==-1|caoeGG609qZy9pr8FMaZrA==-1|QD7L6h1uI0thGVfk7BAHZg==-1|ruwklTy8nafLCEsQEewr5A==-1|V7l/tXQ+1ynUiNXNKr5Z0A==-1|ORwuXco6Vknw11jck+8i1A==-1|STU1e0R6K5iuG0QCYPBrDQ==-1|7Xmb5cXp0QuOnakkSlU5YA==-1|hXQMTHJUBCkQQ+hIZLuPig==-1|t02TVC8DD642W8NQATX5Tg==-1|LAhS9QdrTReG3OGT/VTuuQ==-1|ufR/4cOnJ7iaf8VOCT7IfQ==-1|70qfTVvAlXKfeUTZo+pPGw==-1|R9PYhB6AdspwxrJ5UhJa8w==-1|brKHXJBt1ecX375oOhhgtw==-1|CYp5hmRCRgaFGoX86/QqzA==-1|HdLAeoJqYI7E3i/S65CktA==-1|AIErdIHH4bmzoXXffE3bSA==-1|e66U2xGdgJEzdtIHCgCsjg==-1|p674wgv3W/pi4NWBrz0iAQ==-1|ACPh2iFYEV3OmVeKf5w2sQ==-1|fKATin6yQalA3LxtkV6pdw==-1|C1GymtlZCB6R/EywELb5sA==-1|bEJRutBiG42WTUu//w8BVA==-1|+ePml1nf59eEV1peYtUfKQ==-1|z3AIaBOscBlrjRsZUWGGTQ==-1|2dOaq3wYVjP3syp1zQ8eng==-1|br/zstW2+tD4ezyWVlgfLg==-1|WmTsI1TWmNptO2eyJu33CQ==-1|pz+fSOiP+arowmRFb3jNuA==-1|zR9XU807zrqdl9M4BLiojg==-1|78RUqmJDbe/hUXv/XBVOSA==-1|L6hcWqVxRlFNlYYQsHtmDA==-1|ti7LQ2FEPbHgWjXH2/ppYw==-1|yO0DmD7F4W3jhPtv9esj2g==-1|jmfLSaYBe54fgerog9K+dQ==-1|S0Nek2jtsb5CCVisse1P9g==-1|QMQLsrYK+/qBeMvuiD57DA==-1|sF1CjEhIgJ/KaqO+UodWqg==-1|bngsZXz3Ubn23pnkcuNXkQ==-1|k31XMZ1m8jq3LtXyJK/5lQ==-1|kur2FtP4jxtAKkB3CmojCQ==-1|6oGTPJgewW2xWab46YAOLg==-1|+N7gWnSQzXeiE60jDjz+FA==-1|drDzGfozccB0xl4z2iFIAg==-1|MyJEXYFJxq1BBY+PGVwtog==-1|LZVWVBu2tjthG835A9i3tQ==-1|v9B5Zs8D3h/PlnqdE9p2cA==-1|gePvq1Gj6Tb7+KEeZ6SMSA==-1|7p77L24ipmyiE60jDjz+FA==-1|jCiyudIy9q2b6Vo//pcpDg==-1|ByfS7WuwKWSBeMvuiD57DA==-1|ISpViFLUG7aiKP7LF4+WSQ==-1|NSE2Drg51mULJQaqW9t7/Q==-1|owfI6e8D/oDPEsUwXp5AJg==-1|6KXXYB9o3wAvswSaA+8gjA==-1|pSFmEXCKGwWV0Iz5157sew==-1|TEVaSKsPHjIYvwxzBJzVZA==-1|6WWnYSkh9DbW3a1QXEwmjw==-1|uwkF8oYnpF7i0lG5RJZVBw==-1|gjILWjY1nR6JwVl46ZHVJw==-1|M93g+D3cMqRuXeYKCfJsXQ==-1|/u5COgC3xOmCRLDn18rR/Q==-1|nMQ94PqM9xNcQRs7Mh/jNA==-1|t8ZHvXfm/bX0ELpiLXgZMw==-1";
        // url=URLEncoder.encode(url,"UTF-8");

        String url = "4iumB+SsF1V+MNICp0AlOg==-1|f/UMZfwXelfPlnqdE9p2cA==-1|4qmHvoFlcYhbxRSwszuRZA==-1|oWlptcPWYxQI/Eo7qlElgA==-1|KVClyIQ6qOkNjNW7zqoVCw==-1|vtNYWESvf+dYBbw7unikSQ==-1|oiSYjgCHape2K46p1OG58g==-1|S2nJt6jPwcRrjRsZUWGGTQ==-1|d5ts0O3Kc94cDH1CSg82eA==-1|RjM1LXPOuqDY4nUZwhDMXA==-1|5OQa5d074p41SwltPb6fmw==-1|YiswVyAGQDVZGraJHFSCmQ==-1|VNRyM0ff4UK8WuhkIOFbuQ==-1|BALUi2UkCbq+ZzOwUtyNBg==-1|w+g0Lr6koWrEfl931OO54A==-1|NlwMj1EnLuaOnakkSlU5YA==-1|U9hWY1ojWjOG3OGT/VTuuQ==-1|flLFh5fl7PwsroQUEoEnag==-1|bl1PM31Ztpw8xL7lDo0RWg==-1|wirgAmWWfDG3o7vgXZsHpg==-1|cr4Lgj3tQtevbQX7J4iVLA==-1|1kTRC5a8SXzRFrOFZyDDaA==-1|Y/KdJUUhZunMIukWyyoJhg==-1|oJrEA0B46UTx6S4jbzf+1A==-1|ROy6knUlQEw5pax33OliVA==-1|be3QPNge8TjJzuZpZVj23Q==-1|EZDLnKSOzpfMabjPxp1W4g==-1|muT/t8DOTo2Sl19B8/FCGg==-1|k258tCRhQF5YG4BiWJM1Zg==-1|F20DWT4aGoFW9ExExmF6OA==-1|/i9e+56KCkbOm8IRddvPww==-1|HnTkWigicawuic61nxT5iw==-1|lm9/jpl+9J/15jOgQGrxpQ==-1|vEOJHcMbfgKb//84qUV/zA==-1|wdx5zkSVmdnt5xiilxdBmw==-1|ckAOxNBusOJZ7yfcWRCZuA==-1|caoeGG609qZy9pr8FMaZrA==-1|QD7L6h1uI0thGVfk7BAHZg==-1|ruwklTy8nafLCEsQEewr5A==-1|V7l/tXQ+1ynUiNXNKr5Z0A==-1|ORwuXco6Vknw11jck+8i1A==-1|STU1e0R6K5iuG0QCYPBrDQ==-1|7Xmb5cXp0QuOnakkSlU5YA==-1|hXQMTHJUBCkQQ+hIZLuPig==-1|t02TVC8DD642W8NQATX5Tg==-1|LAhS9QdrTReG3OGT/VTuuQ==-1|ufR/4cOnJ7iaf8VOCT7IfQ==-1|70qfTVvAlXKfeUTZo+pPGw==-1|R9PYhB6AdspwxrJ5UhJa8w==-1|brKHXJBt1ecX375oOhhgtw==-1|CYp5hmRCRgaFGoX86/QqzA==-1|HdLAeoJqYI7E3i/S65CktA==-1|AIErdIHH4bmzoXXffE3bSA==-1|e66U2xGdgJEzdtIHCgCsjg==-1|p674wgv3W/pi4NWBrz0iAQ==-1|ACPh2iFYEV3OmVeKf5w2sQ==-1|fKATin6yQalA3LxtkV6pdw==-1|C1GymtlZCB6R/EywELb5sA==-1|bEJRutBiG42WTUu//w8BVA==-1|+ePml1nf59eEV1peYtUfKQ==-1|z3AIaBOscBlrjRsZUWGGTQ==-1|2dOaq3wYVjP3syp1zQ8eng==-1|br/zstW2+tD4ezyWVlgfLg==-1|WmTsI1TWmNptO2eyJu33CQ==-1|pz+fSOiP+arowmRFb3jNuA==-1|zR9XU807zrqdl9M4BLiojg==-1|78RUqmJDbe/hUXv/XBVOSA==-1|L6hcWqVxRlFNlYYQsHtmDA==-1|ti7LQ2FEPbHgWjXH2/ppYw==-1|yO0DmD7F4W3jhPtv9esj2g==-1|jmfLSaYBe54fgerog9K+dQ==-1|S0Nek2jtsb5CCVisse1P9g==-1|QMQLsrYK+/qBeMvuiD57DA==-1|sF1CjEhIgJ/KaqO+UodWqg==-1|bngsZXz3Ubn23pnkcuNXkQ==-1|k31XMZ1m8jq3LtXyJK/5lQ==-1|kur2FtP4jxtAKkB3CmojCQ==-1|6oGTPJgewW2xWab46YAOLg==-1|+N7gWnSQzXeiE60jDjz+FA==-1|drDzGfozccB0xl4z2iFIAg==-1|MyJEXYFJxq1BBY+PGVwtog==-1|LZVWVBu2tjthG835A9i3tQ==-1|v9B5Zs8D3h/PlnqdE9p2cA==-1|gePvq1Gj6Tb7+KEeZ6SMSA==-1|7p77L24ipmyiE60jDjz+FA==-1|jCiyudIy9q2b6Vo//pcpDg==-1|ByfS7WuwKWSBeMvuiD57DA==-1|ISpViFLUG7aiKP7LF4+WSQ==-1|NSE2Drg51mULJQaqW9t7/Q==-1|owfI6e8D/oDPEsUwXp5AJg==-1|6KXXYB9o3wAvswSaA+8gjA==-1|pSFmEXCKGwWV0Iz5157sew==-1|TEVaSKsPHjIYvwxzBJzVZA==-1|6WWnYSkh9DbW3a1QXEwmjw==-1|uwkF8oYnpF7i0lG5RJZVBw==-1|gjILWjY1nR6JwVl46ZHVJw==-1|M93g+D3cMqRuXeYKCfJsXQ==-1|/u5COgC3xOmCRLDn18rR/Q==-1|nMQ94PqM9xNcQRs7Mh/jNA==-1|t8ZHvXfm/bX0ELpiLXgZMw==-1";

        // url =
        // "data=sMXfQw2bmIWiE60jDjz+FA==-1|fOMNl0ql2zZ7PNhUz0BbdA==-1|fMrtmUDUfR/Z43oiaSkKBA==-1|DefHkT51NFaG3OGT/VTuuQ==-1|uoYzYTu2ci8OnQPZ+hdhUg==-1|zXJyFXI7qEV6DmSjEnDP3g==-1|YbWEQNJxqA8/gxmuRWsfTA==-1|qFL/ju7Kwu4qokdFGKqdpg==-1|H/pfFHcqE0FH/wMDr5iIfA==-1|2sa4enkncn4Qh9BKg59PuA==-1|GppKofHvkn0ick++/Ls68Q==-1|1U3lmhUVBLOpRVutMh8ENA==-1|gJg9RNOoYuaOnakkSlU5YA==-1|9KqNcD0zhA37+KEeZ6SMSA==-1|BSOMYkgGTRBrxR29y2mMgw==-1|yRZ7Hul3wYJ6KigrjOBOUQ==-1|Oqs+hogQp5zH0jigrs6ESw==-1|PwqXvwtFLA5J6/XsTwWKCg==-1|dLxEpPEIv1IV8cLODhdGgg==-1|b2JqJkPaL4UiOmFlnmvQbA==-1|93GSckZJ14HKOKrjJF00wQ==-1|rM4v29Zabs9LkBgtc9+8NQ==-1|FFYpaB8yVwgGXNmh9j/Qww==-1|kmJgfYjpF35Z62EvjKQsPg==-1|7HthP9bjZ5hEAtDKXEHUdQ==-1|krdg56x2h+rrcR7wDGecLw==-1|Yaa2M2TiWKdY/yaaJul/DQ==-1|41yw32relB2rxqeQlthz/g==-1|DhAyC3IgyrXx6S4jbzf+1A==-1|kjFWCdfcxY8W+7h3M8Aukw==-1|Gdq1aK8W/iOdlfzw/ZbYUA==-1|ra9W2gDv57oQh9BKg59PuA==-1|1LVq622To/fGeypUGy5WAA==-1|pZxEQtGXzGBhGVfk7BAHZg==-1|5Hx31iq50K8EEHuWgiVsVw==-1|UZ2biov1zIuvZjwk5pGmAw==-1|h2PypBgjFScBYbWApNfeug==-1|UT0FLYGEHuDA54LseTblfQ==-1|CYpq2yUmjJ/HrZKDNfX+vQ==-1|rwCk8rBhAhMSedqUhmT74g==-1|3juV5f/6SPwXPto4qfIe1g==-1|5YROB+iUeJUX375oOhhgtw==-1|+C6winBe2bXowmRFb3jNuA==-1|oSWF7ECZ1o23fdVJ4Mxoog==-1|+OrElTtn55kpLPEsfcWG9Q==-1|UTyMLRaCM/vlaeLKbuFqZA==-1|I5S2hgxoekczgY2tvD2YZg==-1|UhIIGCA1molUzsWSNHf6sQ==-1|ow5hlCMMnORAKkB3CmojCQ==-1|R94oSlhmNmnygR+RqzS/8A==-1|V5ogIHn4xVmPoWFoWlzxyw==-1|oTcLDmCgpP7x6S4jbzf+1A==-1|6E5GSMeLZ9o6F2bRkSY0pw==-1|6RTD7m//1lIzgY2tvD2YZg==-1|fXiUG2RoKYmh/y7vv/pKPw==-1|gS7nwPlXNJD1dvwjbDcmsQ==-1|RiVn7XrjucuKgjHllBQEiQ==-1|7mW5Z4PenZ4u5x3WRLFSPQ==-1|NpryuTj/aPnGTuZhAjJP0w==-1|3t8gkzGXLnJ0xl4z2iFIAg==-1|cKIx3oM4z2/E3i/S65CktA==-1|rfPr6DEjqmdp6WtIRi2GOA==-1|yRQKvVMaHhJT8HoKMl1WUw==-1|Zk5i9rENcRjYJT+qM7YMqw==-1|ejks5+IbOHdw8dUqz0WUAA==-1|Mx6pgfWVLxYick++/Ls68Q==-1|296a9fPXJR55H4OEPa/OoA==-1|WTTSgRL7pC2nHxDQkgGTYg==-1|s6D14AjK7fBAKkB3CmojCQ==-1|VtcgBP8L/w3HrZKDNfX+vQ==-1|wYhd60GZfMz84G9f6EQUKw==-1|y+BmsNoVWgduZqxfsxAS2Q==-1|P6T3+wt1AzJNj9nSiIzkfQ==-1|puOGCoBIG9jf2O7bZtQiQA==-1|ETbZOQDibzaNuEwC8OBAkQ==-1|4V5dFu4Zv4H1kkCY7JFhbg==-1|RNwrsngjnAE0OblLH1Ab8A==-1|VPcySJrYDKkU3GaUsgcDwg==-1|hNHukZCG5CKBeMvuiD57DA==-1|k0t5G8j+GA15H4OEPa/OoA==-1|Eyds+dBzXZxYBbw7unikSQ==-1|1gzoPmZwxi0oBwYgXRfF5Q==-1|HMWd0Wx2UMyA+yaw3TRdkg==-1|p4a7ucH7kL0FST36tFZTuA==-1|uKErpxa2k2whEZouAJSXuw==-1|8NvYIFW4PV/e85wDAsvDog==-1|zUs8XxyFQFk3F7c7OwYfGA==-1|7kE2F4rPNqcYVjzqXjzBaA==-1|hO1SC/ig6ccXpwMyLNeH3A==-1|HIb9+OK2jD5GJbsee/zkng==-1|Cr8+pBl0Y4sOpVKr/A+1GA==-1|9cl5F7ZyCequs3x63DsG1Q==-1|bEBrPYxS0GwoNI1H2QGxRg==-1|HFVRL/948uqp5cu9MFnqWg==-1|ovotOJDhF3AL+i1HsPJwAQ==-1|WwtYLgrZlfIqokdFGKqdpg==-1|pZKj0PXMNGldZcFrcwx1Yw==-1|u7jl9krLLfoZfFzxnQOOsQ==-1|91I+BxbJnj87LBcB7Z9w7Q==-1|PZ3r9fuVhRVcLASZsFtEIw==-1";
        // url = "k/b8PNdlo+sOLqalB8U4cg==-1";
//		url = URLEncoder.encode(url, "UTF-8");
        UtilHttp http = new UtilHttp();
        http.setHost("https://www.1036225283.com").setParam("");
        String result = http.post();
        System.out.println(result);

    }


    public UtilHttp setCookie(String strCookie) {
        this.strCookie = strCookie;
        return this;
    }

    public UtilHttp setHost(String strHost) {
        this.strHost = strHost;
        return this;
    }

    public UtilHttp setParam(String param) {
        this.param.append(param);
        return this;
    }

    public String post() throws Exception {
        /**
         * 新建连接，打开连接
         * */
        URL url = new URL(strHost);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        /**
         * 设置输入输出流
         * */
        connection.setDoOutput(true);
        connection.setDoInput(true);
        /**
         * 设置POST方式
         */
        connection.setRequestMethod("POST");
        /**
         * 设置是否可以使用缓存
         */
        connection.setUseCaches(false);
        /**
         * 设置是否可以获取头域
         */
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

        connection.setRequestProperty("User-Agent", "Mozilla/5.0 ( compatible ) ");

        connection.setRequestProperty("Accept", "*/*");

        /**
         * 设置块大小
         * */

        if (strCookie != null) {
            connection.setRequestProperty("Cookie", strCookie);
        }

        connection.connect();
        /**
         * 给post请求设值
         */
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        bufferedWriter.append(param.toString());
        bufferedWriter.flush();
        // OutputStream outputStream = connection.getOutputStream();// 输入参数
        // outputStream.write(param.toString().getBytes("UTF-8"));

        /**
         * 输入流
         */
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuffer sb = new StringBuffer();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        return sb.toString();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

}
