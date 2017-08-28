package com.example.panda.model.live.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2017/8/26.
 */

public class OriBean {


    /**
     * video : [{"em":"CM01","img":"http://p4.img.cctvpic.com/fmspic/2017/08/25/026839787dfb4eb597e724e835b44782-69.jpg?p=2&h=120","len":"00:01:58","order":"24","ptime":"2017-08-25 12:19:27","t":"《当熊不让》 20170825 第二十四期：大熊猫生日扎堆是什么体验？","url":"http://tv.cntv.cn/video/VSET100332640004/026839787dfb4eb597e724e835b44782","vid":"026839787dfb4eb597e724e835b44782","vsid":"VSET100332640004"},{"em":"CM01","img":"http://p5.img.cctvpic.com/fmspic/2017/08/18/dee52ff49d7c440baa78a9aa338ccf67-50.jpg?p=2&h=120","len":"00:01:47","order":"23","ptime":"2017-08-18 13:12:40","t":"《当熊不让》 20170818 第二十三期：你要的萌态我都有","url":"http://tv.cntv.cn/video/VSET100332640004/dee52ff49d7c440baa78a9aa338ccf67","vid":"dee52ff49d7c440baa78a9aa338ccf67","vsid":"VSET100332640004"},{"em":"CM01","img":"http://p4.img.cctvpic.com/fmspic/2017/08/11/35dbfb5c78d54e23ba328ce7d083c1cd-50.jpg?p=2&h=120","len":"00:01:16","order":"22","ptime":"2017-08-11 13:35:44","t":"《当熊不让》 20170811 第二十二期：悄悄告诉你，家门口才是风水宝地","url":"http://tv.cntv.cn/video/VSET100332640004/35dbfb5c78d54e23ba328ce7d083c1cd","vid":"35dbfb5c78d54e23ba328ce7d083c1cd","vsid":"VSET100332640004"},{"em":"CM01","img":"http://p1.img.cctvpic.com/fmspic/2017/07/28/85497ad11a39407ca5e03f8952d0d7e3-50.jpg?p=2&h=120","len":"00:01:39","order":"21","ptime":"2017-07-28 15:10:53","t":"《当熊不让》 20170728 第二十一期：见过美的，没见过我这么美的！","url":"http://tv.cntv.cn/video/VSET100332640004/85497ad11a39407ca5e03f8952d0d7e3","vid":"85497ad11a39407ca5e03f8952d0d7e3","vsid":"VSET100332640004"},{"em":"CM01","img":"http://p3.img.cctvpic.com/fmspic/2017/07/21/19726f36d1cc4299b16e881fe0a2a8ab-50.jpg?p=2&h=120","len":"00:01:37","order":"20","ptime":"2017-07-21 12:51:41","t":"《当熊不让》 20170721 第二十期：国内\u2014新生团子美颜盛世稳坐榜首","url":"http://tv.cntv.cn/video/VSET100332640004/19726f36d1cc4299b16e881fe0a2a8ab","vid":"19726f36d1cc4299b16e881fe0a2a8ab","vsid":"VSET100332640004"},{"em":"CM01","img":"http://p1.img.cctvpic.com/fmspic/2017/07/14/77ee585276254a8d920114ce312f87d1-79.jpg?p=2&h=120","len":"00:02:18","order":"19","ptime":"2017-07-14 13:19:43","t":"《当熊不让》 20170714 第十九期：热在三伏天，不服都不行","url":"http://tv.cntv.cn/video/VSET100332640004/77ee585276254a8d920114ce312f87d1","vid":"77ee585276254a8d920114ce312f87d1","vsid":"VSET100332640004"},{"em":"CM01","img":"http://p3.img.cctvpic.com/fmspic/2017/07/07/19852d41dcc74d4dadcc66b91c6d258d-70.jpg?p=2&h=120","len":"00:01:59","order":"18","ptime":"2017-07-07 15:06:35","t":"《当熊不让》 20170707 第十八期：想要伺候\u201c灰大王\u201d，得先练有个好手艺！","url":"http://tv.cntv.cn/video/VSET100332640004/19852d41dcc74d4dadcc66b91c6d258d","vid":"19852d41dcc74d4dadcc66b91c6d258d","vsid":"VSET100332640004"}]
     * videoset : {"0":{"bj":"","cd":"","desc":"新节目《当熊不让》上线啦！每周五盘点当周最火视频和图片，让你一次看遍人气熊猫！","dy":"","enname":"其他","fcl":"","fl":"熊猫直播","img":"http://p1.img.cctvpic.com/photoAlbum/vms/standard/img/2017/3/15/VSETgpbnd9sJ0BP2qfKq00j2170315.jpg","js":"","name":"当熊不让","nf":"","playdesc":"","relvsid":"","sbpd":"其他","sbsj":"","url":"http://tv.cntv.cn/videoset/VSET100332640004","vsid":"VSET100332640004","yz":"","zcr":"","zy":""},"count":"24"}
     */

    private VideosetBean videoset;
    private List<VideoBean> video;

    public VideosetBean getVideoset() {
        return videoset;
    }

    public void setVideoset(VideosetBean videoset) {
        this.videoset = videoset;
    }

    public List<VideoBean> getVideo() {
        return video;
    }

    public void setVideo(List<VideoBean> video) {
        this.video = video;
    }

    public static class VideosetBean {
        /**
         * 0 : {"bj":"","cd":"","desc":"新节目《当熊不让》上线啦！每周五盘点当周最火视频和图片，让你一次看遍人气熊猫！","dy":"","enname":"其他","fcl":"","fl":"熊猫直播","img":"http://p1.img.cctvpic.com/photoAlbum/vms/standard/img/2017/3/15/VSETgpbnd9sJ0BP2qfKq00j2170315.jpg","js":"","name":"当熊不让","nf":"","playdesc":"","relvsid":"","sbpd":"其他","sbsj":"","url":"http://tv.cntv.cn/videoset/VSET100332640004","vsid":"VSET100332640004","yz":"","zcr":"","zy":""}
         * count : 24
         */

        @SerializedName("0")
        private _$0Bean _$0;
        private String count;

        public _$0Bean get_$0() {
            return _$0;
        }

        public void set_$0(_$0Bean _$0) {
            this._$0 = _$0;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public static class _$0Bean {
            /**
             * bj :
             * cd :
             * desc : 新节目《当熊不让》上线啦！每周五盘点当周最火视频和图片，让你一次看遍人气熊猫！
             * dy :
             * enname : 其他
             * fcl :
             * fl : 熊猫直播
             * img : http://p1.img.cctvpic.com/photoAlbum/vms/standard/img/2017/3/15/VSETgpbnd9sJ0BP2qfKq00j2170315.jpg
             * js :
             * name : 当熊不让
             * nf :
             * playdesc :
             * relvsid :
             * sbpd : 其他
             * sbsj :
             * url : http://tv.cntv.cn/videoset/VSET100332640004
             * vsid : VSET100332640004
             * yz :
             * zcr :
             * zy :
             */

            private String bj;
            private String cd;
            private String desc;
            private String dy;
            private String enname;
            private String fcl;
            private String fl;
            private String img;
            private String js;
            private String name;
            private String nf;
            private String playdesc;
            private String relvsid;
            private String sbpd;
            private String sbsj;
            private String url;
            private String vsid;
            private String yz;
            private String zcr;
            private String zy;

            public String getBj() {
                return bj;
            }

            public void setBj(String bj) {
                this.bj = bj;
            }

            public String getCd() {
                return cd;
            }

            public void setCd(String cd) {
                this.cd = cd;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getDy() {
                return dy;
            }

            public void setDy(String dy) {
                this.dy = dy;
            }

            public String getEnname() {
                return enname;
            }

            public void setEnname(String enname) {
                this.enname = enname;
            }

            public String getFcl() {
                return fcl;
            }

            public void setFcl(String fcl) {
                this.fcl = fcl;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getJs() {
                return js;
            }

            public void setJs(String js) {
                this.js = js;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNf() {
                return nf;
            }

            public void setNf(String nf) {
                this.nf = nf;
            }

            public String getPlaydesc() {
                return playdesc;
            }

            public void setPlaydesc(String playdesc) {
                this.playdesc = playdesc;
            }

            public String getRelvsid() {
                return relvsid;
            }

            public void setRelvsid(String relvsid) {
                this.relvsid = relvsid;
            }

            public String getSbpd() {
                return sbpd;
            }

            public void setSbpd(String sbpd) {
                this.sbpd = sbpd;
            }

            public String getSbsj() {
                return sbsj;
            }

            public void setSbsj(String sbsj) {
                this.sbsj = sbsj;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getVsid() {
                return vsid;
            }

            public void setVsid(String vsid) {
                this.vsid = vsid;
            }

            public String getYz() {
                return yz;
            }

            public void setYz(String yz) {
                this.yz = yz;
            }

            public String getZcr() {
                return zcr;
            }

            public void setZcr(String zcr) {
                this.zcr = zcr;
            }

            public String getZy() {
                return zy;
            }

            public void setZy(String zy) {
                this.zy = zy;
            }
        }
    }

    public static class VideoBean {
        /**
         * em : CM01
         * img : http://p4.img.cctvpic.com/fmspic/2017/08/25/026839787dfb4eb597e724e835b44782-69.jpg?p=2&h=120
         * len : 00:01:58
         * order : 24
         * ptime : 2017-08-25 12:19:27
         * t : 《当熊不让》 20170825 第二十四期：大熊猫生日扎堆是什么体验？
         * url : http://tv.cntv.cn/video/VSET100332640004/026839787dfb4eb597e724e835b44782
         * vid : 026839787dfb4eb597e724e835b44782
         * vsid : VSET100332640004
         */

        private String em;
        private String img;
        private String len;
        private String order;
        private String ptime;
        private String t;
        private String url;
        private String vid;
        private String vsid;

        public String getEm() {
            return em;
        }

        public void setEm(String em) {
            this.em = em;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getLen() {
            return len;
        }

        public void setLen(String len) {
            this.len = len;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getT() {
            return t;
        }

        public void setT(String t) {
            this.t = t;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
        }

        public String getVsid() {
            return vsid;
        }

        public void setVsid(String vsid) {
            this.vsid = vsid;
        }
    }
}
