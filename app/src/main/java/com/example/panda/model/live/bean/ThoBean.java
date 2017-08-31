package com.example.panda.model.live.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2017/8/26.
 */

public class ThoBean {
    /**
     * video : [{"em":"CM01","img":"http://p4.img.cctvpic.com/fmspic/2017/07/26/2401534df1b14d76a661a30ff17f2e6b-249.jpg?p=2&h=120","len":"00:07:38","order":"116","ptime":"2017-07-26 18:21:11","t":"《熊猫那些事儿》 20170726 第三季第二十期 熊猫视频日记\u2014\u2014爱要大声说出来","url":"http://tv.cntv.cn/video/VSET100237714751/2401534df1b14d76a661a30ff17f2e6b","vid":"2401534df1b14d76a661a30ff17f2e6b","vsid":"VSET100237714751"},{"em":"CM01","img":"http://p3.img.cctvpic.com/fmspic/2017/07/05/51adae6767aa43ff8dee0a93dcbdd117-129.jpg?p=2&h=120","len":"00:04:11","order":"115","ptime":"2017-07-05 13:15:46","t":"《熊猫那些事儿》 20170705 第三季第19期：萌团KTV之儿歌串烧","url":"http://tv.cntv.cn/video/VSET100237714751/51adae6767aa43ff8dee0a93dcbdd117","vid":"51adae6767aa43ff8dee0a93dcbdd117","vsid":"VSET100237714751"},{"em":"CM01","img":"http://p4.img.cctvpic.com/fmspic/2017/06/28/3eaf3c8d622d402e97b2324fd08f2e95-129.jpg?p=2&h=120","len":"00:04:29","order":"114","ptime":"2017-06-28 13:22:56","t":"《熊猫那些事儿》 20170628 第三季第18期：为什么大熊猫能以卖萌为生？","url":"http://tv.cntv.cn/video/VSET100237714751/3eaf3c8d622d402e97b2324fd08f2e95","vid":"3eaf3c8d622d402e97b2324fd08f2e95","vsid":"VSET100237714751"},{"em":"CM01","img":"http://p4.img.cctvpic.com/fmspic/2017/06/21/3b7a7ae78af24787bc6f584bfc038f91-189.jpg?p=2&h=120","len":"00:06:04","order":"113","ptime":"2017-06-21 14:49:17","t":"《熊猫那些事儿》 20170621 第三季第17期：来自野外大熊猫的声音","url":"http://tv.cntv.cn/video/VSET100237714751/3b7a7ae78af24787bc6f584bfc038f91","vid":"3b7a7ae78af24787bc6f584bfc038f91","vsid":"VSET100237714751"},{"em":"CM01","img":"http://p2.img.cctvpic.com/fmspic/2017/06/14/bc9982397bbc4650990aaad69f60a50e-189.jpg","len":"00:06:03","order":"112","ptime":"2017-06-14 17:21:40","t":"《熊猫那些事儿》 20170614 第三季第16期：他们的熊设智商比你的高","url":"http://tv.cntv.cn/video/VSET100237714751/bc9982397bbc4650990aaad69f60a50e","vid":"bc9982397bbc4650990aaad69f60a50e","vsid":"VSET100237714751"},{"em":"CM01","img":"http://p2.img.cctvpic.com/fmspic/2017/06/07/4b2abfa475434925b83cd778cb155549-129.jpg","len":"00:04:22","order":"111","ptime":"2017-06-07 17:10:03","t":"《熊猫那些事儿》 20170607 第三季第15期：要想高考考的好，还得玩具玩的早","url":"http://tv.cntv.cn/video/VSET100237714751/4b2abfa475434925b83cd778cb155549","vid":"4b2abfa475434925b83cd778cb155549","vsid":"VSET100237714751"},{"em":"CM01","img":"http://p4.img.cctvpic.com/fmspic/2017/05/31/9da49a0531354f2eb0eda412ce5f420a-129.jpg","len":"00:03:56","order":"110","ptime":"2017-05-31 16:29:14","t":"《熊猫那些事儿》 20170531 第三季第14期：看着我眼睛说话，以示尊重","url":"http://tv.cntv.cn/video/VSET100237714751/9da49a0531354f2eb0eda412ce5f420a","vid":"9da49a0531354f2eb0eda412ce5f420a","vsid":"VSET100237714751"}]
     * videoset : {"0":{"bj":"","cd":"","desc":"熊猫频道栏目。","dy":"","enname":"CNTV","fcl":"","fl":"熊猫直播","img":"http://p5.img.cctvpic.com/fmspic/vms/image/2015/07/03/VSET_1435905506188395.jpg","js":"","name":"熊猫那些事儿","nf":"","playdesc":"","relvsid":"","sbpd":"CNTV","sbsj":"","url":"http://tv.cntv.cn/videoset/VSET100237714751","vsid":"VSET100237714751","yz":"","zcr":"","zy":""},"count":"119"}
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
         * 0 : {"bj":"","cd":"","desc":"熊猫频道栏目。","dy":"","enname":"CNTV","fcl":"","fl":"熊猫直播","img":"http://p5.img.cctvpic.com/fmspic/vms/image/2015/07/03/VSET_1435905506188395.jpg","js":"","name":"熊猫那些事儿","nf":"","playdesc":"","relvsid":"","sbpd":"CNTV","sbsj":"","url":"http://tv.cntv.cn/videoset/VSET100237714751","vsid":"VSET100237714751","yz":"","zcr":"","zy":""}
         * count : 119
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
             * desc : 熊猫频道栏目。
             * dy :
             * enname : CNTV
             * fcl :
             * fl : 熊猫直播
             * img : http://p5.img.cctvpic.com/fmspic/vms/image/2015/07/03/VSET_1435905506188395.jpg
             * js :
             * name : 熊猫那些事儿
             * nf :
             * playdesc :
             * relvsid :
             * sbpd : CNTV
             * sbsj :
             * url : http://tv.cntv.cn/videoset/VSET100237714751
             * vsid : VSET100237714751
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
         * img : http://p4.img.cctvpic.com/fmspic/2017/07/26/2401534df1b14d76a661a30ff17f2e6b-249.jpg?p=2&h=120
         * len : 00:07:38
         * order : 116
         * ptime : 2017-07-26 18:21:11
         * t : 《熊猫那些事儿》 20170726 第三季第二十期 熊猫视频日记——爱要大声说出来
         * url : http://tv.cntv.cn/video/VSET100237714751/2401534df1b14d76a661a30ff17f2e6b
         * vid : 2401534df1b14d76a661a30ff17f2e6b
         * vsid : VSET100237714751
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
