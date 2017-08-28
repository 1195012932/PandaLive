package com.example.panda.model.live.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2017/8/28.
 */

public class NotBean {


    /**
     * videoset : {"0":{"vsid":"VSET100167216881","relvsid":"","name":"熊猫频道-精彩一刻","img":"http://p5.img.cctvpic.com/fmspic/vms/image/2013/06/21/VSET_1371809214479325.jpg","enname":"其他","url":"http://tv.cntv.cn/videoset/VSET100167216881","cd":"","zy":"","bj":"","dy":"","js":"","nf":"","yz":"","fl":"","sbsj":"2013-05-01","sbpd":"其他","desc":"精彩一刻栏目关注人气较高的熊猫个体，精选每日直播中最吸引人的画面，呈现熊猫生活中最精彩的状态。","playdesc":"","zcr":"","fcl":""},"count":"4773"}
     * video : [{"vsid":"VSET100167216881","order":"4776","vid":"099b683c10454b87bb4907f6ef63980e","t":"《精彩一刻》 20170827 奶爸，你今天对我有意见蛮？","url":"http://tv.cntv.cn/video/VSET100167216881/099b683c10454b87bb4907f6ef63980e","ptime":"2017-08-27 11:34:11","img":"http://p1.img.cctvpic.com/fmspic/2017/08/27/099b683c10454b87bb4907f6ef63980e-30.jpg?p=2&h=120","len":"00:00:40","em":"CM01"},{"vsid":"VSET100167216881","order":"4775","vid":"26bb0b9e822a42f9959aa450cfa2fc47","t":"《精彩一刻》 20170827 胖到没脖子","url":"http://tv.cntv.cn/video/VSET100167216881/26bb0b9e822a42f9959aa450cfa2fc47","ptime":"2017-08-27 11:32:34","img":"http://p1.img.cctvpic.com/fmspic/2017/08/27/26bb0b9e822a42f9959aa450cfa2fc47-9.jpg?p=2&h=120","len":"00:00:18","em":"CM01"},{"vsid":"VSET100167216881","order":"4777","vid":"f0c6679f2e3f488f9dbb39185afb8202","t":"《精彩一刻》 20170827 头套扯掉了","url":"http://tv.cntv.cn/video/VSET100167216881/f0c6679f2e3f488f9dbb39185afb8202","ptime":"2017-08-27 11:31:35","img":"http://p1.img.cctvpic.com/fmspic/2017/08/27/f0c6679f2e3f488f9dbb39185afb8202-22.jpg?p=2&h=120","len":"00:00:37","em":"CM01"},{"vsid":"VSET100167216881","order":"4774","vid":"672bc692db7a4969a109331ce50977a9","t":"《精彩一刻》 20170827 冰冰：大头煤你吓到我啦","url":"http://tv.cntv.cn/video/VSET100167216881/672bc692db7a4969a109331ce50977a9","ptime":"2017-08-27 11:29:50","img":"http://p4.img.cctvpic.com/fmspic/2017/08/27/672bc692db7a4969a109331ce50977a9-9.jpg?p=2&h=120","len":"00:00:18","em":"CM01"},{"vsid":"VSET100167216881","order":"4773","vid":"daff35910f614312bea9cdadbd515b27","t":"《精彩一刻》 20170827 被卡住咋办？滚滚教你这样做~","url":"http://tv.cntv.cn/video/VSET100167216881/daff35910f614312bea9cdadbd515b27","ptime":"2017-08-27 11:28:17","img":"http://p5.img.cctvpic.com/fmspic/2017/08/27/daff35910f614312bea9cdadbd515b27-20.jpg?p=2&h=120","len":"00:00:20","em":"CM01"},{"vsid":"VSET100167216881","order":"4772","vid":"861feb4d63fb44d090fd21f8517425e3","t":"《精彩一刻》 20170827 福顺，小心肥实亲妈粉哦","url":"http://tv.cntv.cn/video/VSET100167216881/861feb4d63fb44d090fd21f8517425e3","ptime":"2017-08-27 11:26:38","img":"http://p5.img.cctvpic.com/fmspic/2017/08/27/861feb4d63fb44d090fd21f8517425e3-34.jpg?p=2&h=120","len":"00:00:48","em":"CM01"},{"vsid":"VSET100167216881","order":"4770","vid":"934f5d32a6d64811a21329c372c78e43","t":"《精彩一刻》 20170827 没有笋笋了，那就窝头吧","url":"http://tv.cntv.cn/video/VSET100167216881/934f5d32a6d64811a21329c372c78e43","ptime":"2017-08-27 11:14:47","img":"http://p4.img.cctvpic.com/fmspic/2017/08/27/934f5d32a6d64811a21329c372c78e43-33.jpg?p=2&h=120","len":"00:00:45","em":"CM01"}]
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
         * 0 : {"vsid":"VSET100167216881","relvsid":"","name":"熊猫频道-精彩一刻","img":"http://p5.img.cctvpic.com/fmspic/vms/image/2013/06/21/VSET_1371809214479325.jpg","enname":"其他","url":"http://tv.cntv.cn/videoset/VSET100167216881","cd":"","zy":"","bj":"","dy":"","js":"","nf":"","yz":"","fl":"","sbsj":"2013-05-01","sbpd":"其他","desc":"精彩一刻栏目关注人气较高的熊猫个体，精选每日直播中最吸引人的画面，呈现熊猫生活中最精彩的状态。","playdesc":"","zcr":"","fcl":""}
         * count : 4773
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
             * vsid : VSET100167216881
             * relvsid :
             * name : 熊猫频道-精彩一刻
             * img : http://p5.img.cctvpic.com/fmspic/vms/image/2013/06/21/VSET_1371809214479325.jpg
             * enname : 其他
             * url : http://tv.cntv.cn/videoset/VSET100167216881
             * cd :
             * zy :
             * bj :
             * dy :
             * js :
             * nf :
             * yz :
             * fl :
             * sbsj : 2013-05-01
             * sbpd : 其他
             * desc : 精彩一刻栏目关注人气较高的熊猫个体，精选每日直播中最吸引人的画面，呈现熊猫生活中最精彩的状态。
             * playdesc :
             * zcr :
             * fcl :
             */

            private String vsid;
            private String relvsid;
            private String name;
            private String img;
            private String enname;
            private String url;
            private String cd;
            private String zy;
            private String bj;
            private String dy;
            private String js;
            private String nf;
            private String yz;
            private String fl;
            private String sbsj;
            private String sbpd;
            private String desc;
            private String playdesc;
            private String zcr;
            private String fcl;

            public String getVsid() {
                return vsid;
            }

            public void setVsid(String vsid) {
                this.vsid = vsid;
            }

            public String getRelvsid() {
                return relvsid;
            }

            public void setRelvsid(String relvsid) {
                this.relvsid = relvsid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getEnname() {
                return enname;
            }

            public void setEnname(String enname) {
                this.enname = enname;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCd() {
                return cd;
            }

            public void setCd(String cd) {
                this.cd = cd;
            }

            public String getZy() {
                return zy;
            }

            public void setZy(String zy) {
                this.zy = zy;
            }

            public String getBj() {
                return bj;
            }

            public void setBj(String bj) {
                this.bj = bj;
            }

            public String getDy() {
                return dy;
            }

            public void setDy(String dy) {
                this.dy = dy;
            }

            public String getJs() {
                return js;
            }

            public void setJs(String js) {
                this.js = js;
            }

            public String getNf() {
                return nf;
            }

            public void setNf(String nf) {
                this.nf = nf;
            }

            public String getYz() {
                return yz;
            }

            public void setYz(String yz) {
                this.yz = yz;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getSbsj() {
                return sbsj;
            }

            public void setSbsj(String sbsj) {
                this.sbsj = sbsj;
            }

            public String getSbpd() {
                return sbpd;
            }

            public void setSbpd(String sbpd) {
                this.sbpd = sbpd;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getPlaydesc() {
                return playdesc;
            }

            public void setPlaydesc(String playdesc) {
                this.playdesc = playdesc;
            }

            public String getZcr() {
                return zcr;
            }

            public void setZcr(String zcr) {
                this.zcr = zcr;
            }

            public String getFcl() {
                return fcl;
            }

            public void setFcl(String fcl) {
                this.fcl = fcl;
            }
        }
    }

    public static class VideoBean {
        /**
         * vsid : VSET100167216881
         * order : 4776
         * vid : 099b683c10454b87bb4907f6ef63980e
         * t : 《精彩一刻》 20170827 奶爸，你今天对我有意见蛮？
         * url : http://tv.cntv.cn/video/VSET100167216881/099b683c10454b87bb4907f6ef63980e
         * ptime : 2017-08-27 11:34:11
         * img : http://p1.img.cctvpic.com/fmspic/2017/08/27/099b683c10454b87bb4907f6ef63980e-30.jpg?p=2&h=120
         * len : 00:00:40
         * em : CM01
         */

        private String vsid;
        private String order;
        private String vid;
        private String t;
        private String url;
        private String ptime;
        private String img;
        private String len;
        private String em;

        public String getVsid() {
            return vsid;
        }

        public void setVsid(String vsid) {
            this.vsid = vsid;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getVid() {
            return vid;
        }

        public void setVid(String vid) {
            this.vid = vid;
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

        public String getPtime() {
            return ptime;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
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

        public String getEm() {
            return em;
        }

        public void setEm(String em) {
            this.em = em;
        }
    }
}
