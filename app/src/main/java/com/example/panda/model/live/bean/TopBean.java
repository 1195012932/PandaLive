package com.example.panda.model.live.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2017/8/26.
 */

public class TopBean {

    /**
     * video : [{"em":"CM01","img":"http://p4.img.cctvpic.com/fmspic/2017/08/21/8ff497729f7e4d9881d643f41f90d378-129.jpg?p=2&h=120","len":"00:04:33","order":"61","ptime":"2017-08-21 13:18:46","t":"《熊猫TOP榜》 20170821 第二季第十一期：一起惹的祸，妹妹背黑锅","url":"http://tv.cntv.cn/video/VSET100284428835/8ff497729f7e4d9881d643f41f90d378","vid":"8ff497729f7e4d9881d643f41f90d378","vsid":"VSET100284428835"},{"em":"CM01","img":"http://p2.img.cctvpic.com/fmspic/2017/08/14/5e018d3c4fc24654b2a25b1f430adf5e-129.jpg?p=2&h=120","len":"00:04:03","order":"60","ptime":"2017-08-14 12:52:16","t":"《熊猫TOP榜》 20170814 第二季第十期：滚滚们的幸福生活","url":"http://tv.cntv.cn/video/VSET100284428835/5e018d3c4fc24654b2a25b1f430adf5e","vid":"5e018d3c4fc24654b2a25b1f430adf5e","vsid":"VSET100284428835"},{"em":"CM01","img":"http://p1.img.cctvpic.com/fmspic/2017/08/07/ed3ae7e52f44483f948eaf7ec52c383b-129.jpg?p=2&h=120","len":"00:04:27","order":"59","ptime":"2017-08-07 15:35:07","t":"《熊猫TOP榜》 20170807 第二季第九期：滚滚们个个都是\u201c小熊精\u201d","url":"http://tv.cntv.cn/video/VSET100284428835/ed3ae7e52f44483f948eaf7ec52c383b","vid":"ed3ae7e52f44483f948eaf7ec52c383b","vsid":"VSET100284428835"},{"em":"CM01","img":"http://p2.img.cctvpic.com/fmspic/2017/07/31/75a69204b9144a5590cf30c7d327dfdd-129.jpg?p=2&h=120","len":"00:04:20","order":"58","ptime":"2017-07-31 15:41:43","t":"《熊猫TOP榜》20170731第二季第8期：舞王泰山闪耀回归","url":"http://tv.cntv.cn/video/VSET100284428835/75a69204b9144a5590cf30c7d327dfdd","vid":"75a69204b9144a5590cf30c7d327dfdd","vsid":"VSET100284428835"},{"em":"CM01","img":"http://p5.img.cctvpic.com/fmspic/2017/07/24/32f339ac2009453ea7d212c71cbb89cf-129.jpg?p=2&h=120","len":"00:04:01","order":"57","ptime":"2017-07-24 13:02:50","t":"《熊猫TOP榜》 20170724 第二季第7期.\u201c煤老板\u201d强取豪夺小卖部","url":"http://tv.cntv.cn/video/VSET100284428835/32f339ac2009453ea7d212c71cbb89cf","vid":"32f339ac2009453ea7d212c71cbb89cf","vsid":"VSET100284428835"},{"em":"CM01","img":"http://p3.img.cctvpic.com/fmspic/2017/07/17/3d92fae34dc14b2492de15d5dd122ac8-129.jpg?p=2&h=120","len":"00:04:39","order":"56","ptime":"2017-07-17 15:14:40","t":"《熊猫TOP榜》 20170717 第二季第6期：有这样的奶爸奶妈 欢乐一夏","url":"http://tv.cntv.cn/video/VSET100284428835/3d92fae34dc14b2492de15d5dd122ac8","vid":"3d92fae34dc14b2492de15d5dd122ac8","vsid":"VSET100284428835"},{"em":"CM01","img":"http://p5.img.cctvpic.com/fmspic/2017/07/10/9a29f4991e7a48469729026e18ed9de1-129.jpg?p=2&h=120","len":"00:04:55","order":"55","ptime":"2017-07-10 13:53:27","t":"《熊猫TOP榜》 20170710 第二季第5期：煤炭兄妹的日常之你抢我抢","url":"http://tv.cntv.cn/video/VSET100284428835/9a29f4991e7a48469729026e18ed9de1","vid":"9a29f4991e7a48469729026e18ed9de1","vsid":"VSET100284428835"}]
     * videoset : {"0":{"bj":"","cd":"","desc":"这是一档每周五更新的盘点类节目，每周盘点近期最萌、最酷、最搞笑的直播精彩画面","dy":"","enname":"其他","fcl":"","fl":"","img":"http://p1.img.cctvpic.com/photoAlbum/vms/standard/img/2016/8/5/VSETvxHANBMkCKTqyPatfdBz160805.jpg","js":"","name":"熊猫TOP榜","nf":"","playdesc":"","relvsid":"","sbpd":"其他","sbsj":"","url":"http://tv.cntv.cn/videoset/VSET100284428835","vsid":"VSET100284428835","yz":"","zcr":"","zy":""},"count":"61"}
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
         * 0 : {"bj":"","cd":"","desc":"这是一档每周五更新的盘点类节目，每周盘点近期最萌、最酷、最搞笑的直播精彩画面","dy":"","enname":"其他","fcl":"","fl":"","img":"http://p1.img.cctvpic.com/photoAlbum/vms/standard/img/2016/8/5/VSETvxHANBMkCKTqyPatfdBz160805.jpg","js":"","name":"熊猫TOP榜","nf":"","playdesc":"","relvsid":"","sbpd":"其他","sbsj":"","url":"http://tv.cntv.cn/videoset/VSET100284428835","vsid":"VSET100284428835","yz":"","zcr":"","zy":""}
         * count : 61
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
             * desc : 这是一档每周五更新的盘点类节目，每周盘点近期最萌、最酷、最搞笑的直播精彩画面
             * dy :
             * enname : 其他
             * fcl :
             * fl :
             * img : http://p1.img.cctvpic.com/photoAlbum/vms/standard/img/2016/8/5/VSETvxHANBMkCKTqyPatfdBz160805.jpg
             * js :
             * name : 熊猫TOP榜
             * nf :
             * playdesc :
             * relvsid :
             * sbpd : 其他
             * sbsj :
             * url : http://tv.cntv.cn/videoset/VSET100284428835
             * vsid : VSET100284428835
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
         * img : http://p4.img.cctvpic.com/fmspic/2017/08/21/8ff497729f7e4d9881d643f41f90d378-129.jpg?p=2&h=120
         * len : 00:04:33
         * order : 61
         * ptime : 2017-08-21 13:18:46
         * t : 《熊猫TOP榜》 20170821 第二季第十一期：一起惹的祸，妹妹背黑锅
         * url : http://tv.cntv.cn/video/VSET100284428835/8ff497729f7e4d9881d643f41f90d378
         * vid : 8ff497729f7e4d9881d643f41f90d378
         * vsid : VSET100284428835
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
