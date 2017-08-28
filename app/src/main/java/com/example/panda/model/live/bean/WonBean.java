package com.example.panda.model.live.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by admin on 2017/8/26.
 */

public class WonBean {


    /**
     * video : [{"em":"CM01","img":"http://p3.img.cctvpic.com/fmspic/2017/08/25/c38d42416d604d97bba556438e459a2d-129.jpg?p=2&h=120","len":"00:04:42","order":"253","ptime":"2017-08-25 15:19:43","t":"《特别节目》 20170825 斧头山\u201c吻熊大盗\u201d--梅奶妈","url":"http://tv.cntv.cn/video/VSET100167308855/c38d42416d604d97bba556438e459a2d","vid":"c38d42416d604d97bba556438e459a2d","vsid":"VSET100167308855"},{"em":"CM01","img":"http://p5.img.cctvpic.com/fmspic/2017/08/23/a21f061b34784b8d8fc5894c47f9be57-1272.jpg?p=2&h=120","len":"00:42:22","order":"252","ptime":"2017-08-23 11:24:53","t":"《大熊猫生日季》 20170823 超级明星\u201c宝宝\u201d的生日趴！","url":"http://tv.cntv.cn/video/VSET100167308855/a21f061b34784b8d8fc5894c47f9be57","vid":"a21f061b34784b8d8fc5894c47f9be57","vsid":"VSET100167308855"},{"em":"CM01","img":"http://p3.img.cctvpic.com/fmspic/2017/08/22/4f1bd9a98eb845ddbc0a4a8b27f71062-1929.jpg?p=2&h=120","len":"01:03:37","order":"251","ptime":"2017-08-22 10:38:39","t":"《在现场》 20170822 抱西瓜瞧好戏！看奶爸如何应对三个\u201c熊孩子\u201d","url":"http://tv.cntv.cn/video/VSET100167308855/4f1bd9a98eb845ddbc0a4a8b27f71062","vid":"4f1bd9a98eb845ddbc0a4a8b27f71062","vsid":"VSET100167308855"},{"em":"CM01","img":"http://p2.img.cctvpic.com/fmspic/2017/08/19/d7d1820649cc428eb3f481a43b32ddb8-1809.jpg?p=2&h=120","len":"00:59:32","order":"250","ptime":"2017-08-19 10:09:18","t":"《网红小灰灰\u201c成实\u201d一岁生日快乐》 20170819","url":"http://tv.cntv.cn/video/VSET100167308855/d7d1820649cc428eb3f481a43b32ddb8","vid":"d7d1820649cc428eb3f481a43b32ddb8","vsid":"VSET100167308855"},{"em":"CM01","img":"http://p3.img.cctvpic.com/fmspic/2017/08/18/b933dc80e4e041068d9cf0ea0b253211-1449.jpg?p=2&h=120","len":"00:49:00","order":"249","ptime":"2017-08-18 11:47:02","t":"《大熊猫生日季》 20170818 看\u201c青青\u201d\u201c冰冰\u201d如何消灭生日蛋糕","url":"http://tv.cntv.cn/video/VSET100167308855/b933dc80e4e041068d9cf0ea0b253211","vid":"b933dc80e4e041068d9cf0ea0b253211","vsid":"VSET100167308855"},{"em":"CM01","img":"http://p2.img.cctvpic.com/fmspic/2017/08/14/2434129f1b0e441da4c0e6f892a16489-20.jpg?p=2&h=120","len":"00:00:31","order":"248","ptime":"2017-08-14 14:05:54","t":"《特别节目》 20170814 你说，到底要好瘦才够","url":"http://tv.cntv.cn/video/VSET100167308855/2434129f1b0e441da4c0e6f892a16489","vid":"2434129f1b0e441da4c0e6f892a16489","vsid":"VSET100167308855"},{"em":"CM01","img":"http://p4.img.cctvpic.com/fmspic/2017/08/07/2436565040a543d1b495613f9863084c-4329.jpg?p=2&h=120","len":"02:24:15","order":"247","ptime":"2017-08-06 22:16:52","t":"《燃爆2017 熊猫频道四周年》 20170806","url":"http://tv.cntv.cn/video/VSET100167308855/2436565040a543d1b495613f9863084c","vid":"2436565040a543d1b495613f9863084c","vsid":"VSET100167308855"}]
     * videoset : {"0":{"bj":"","cd":"","desc":"熊猫频道采访野生动物保护专家、熊猫守护使、到访基地的名人，以人类的角度展现熊猫世界。","dy":"","enname":"其他","fcl":"","fl":"熊猫直播","img":"http://p1.img.cctvpic.com/fmspic/vms/image/2013/06/21/VSET_1371809044488847.jpg","js":"","name":"熊猫频道-特别节目","nf":"","playdesc":"","relvsid":"","sbpd":"其他","sbsj":"2013-05-01","url":"http://tv.cntv.cn/videoset/VSET100167308855","vsid":"VSET100167308855","yz":"","zcr":"","zy":""},"count":"259"}
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
         * 0 : {"bj":"","cd":"","desc":"熊猫频道采访野生动物保护专家、熊猫守护使、到访基地的名人，以人类的角度展现熊猫世界。","dy":"","enname":"其他","fcl":"","fl":"熊猫直播","img":"http://p1.img.cctvpic.com/fmspic/vms/image/2013/06/21/VSET_1371809044488847.jpg","js":"","name":"熊猫频道-特别节目","nf":"","playdesc":"","relvsid":"","sbpd":"其他","sbsj":"2013-05-01","url":"http://tv.cntv.cn/videoset/VSET100167308855","vsid":"VSET100167308855","yz":"","zcr":"","zy":""}
         * count : 259
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
             * desc : 熊猫频道采访野生动物保护专家、熊猫守护使、到访基地的名人，以人类的角度展现熊猫世界。
             * dy :
             * enname : 其他
             * fcl :
             * fl : 熊猫直播
             * img : http://p1.img.cctvpic.com/fmspic/vms/image/2013/06/21/VSET_1371809044488847.jpg
             * js :
             * name : 熊猫频道-特别节目
             * nf :
             * playdesc :
             * relvsid :
             * sbpd : 其他
             * sbsj : 2013-05-01
             * url : http://tv.cntv.cn/videoset/VSET100167308855
             * vsid : VSET100167308855
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
         * img : http://p3.img.cctvpic.com/fmspic/2017/08/25/c38d42416d604d97bba556438e459a2d-129.jpg?p=2&h=120
         * len : 00:04:42
         * order : 253
         * ptime : 2017-08-25 15:19:43
         * t : 《特别节目》 20170825 斧头山“吻熊大盗”--梅奶妈
         * url : http://tv.cntv.cn/video/VSET100167308855/c38d42416d604d97bba556438e459a2d
         * vid : c38d42416d604d97bba556438e459a2d
         * vsid : VSET100167308855
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
