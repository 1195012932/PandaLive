package com.example.panda.view.fragment.video.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lenovo on 2017/8/31.
 */

public class VideoItemBean {

    /**
     * videoset : {"0":{"vsid":"VSET100284428835","relvsid":"","name":"熊猫TOP榜","img":"http://p1.img.cctvpic.com/photoAlbum/vms/standard/img/2016/8/5/VSETvxHANBMkCKTqyPatfdBz160805.jpg","enname":"其他","url":"http://tv.cntv.cn/videoset/VSET100284428835","cd":"","zy":"","bj":"","dy":"","js":"","nf":"","yz":"","fl":"","sbsj":"","sbpd":"其他","desc":"这是一档每周五更新的盘点类节目，每周盘点近期最萌、最酷、最搞笑的直播精彩画面","playdesc":"","zcr":"","fcl":""},"count":"62"}
     * video : [{"vsid":"VSET100284428835","order":"48","vid":"06e4ebb0c3e14be4b2bc544807269c8b","t":"《熊猫TOP榜》 20170522 第四十八期：不吃苹果的熊猫不是乖滚滚","url":"http://tv.cntv.cn/video/VSET100284428835/06e4ebb0c3e14be4b2bc544807269c8b","ptime":"2017-05-22 15:04:15","img":"http://p1.img.cctvpic.com/fmspic/2017/05/22/06e4ebb0c3e14be4b2bc544807269c8b-105.jpg","len":"00:03:11","em":"CM01"},{"vsid":"VSET100284428835","order":"47","vid":"16456a7943be474498e21b8b8f33f952","t":"《熊猫TOP榜》 20170515 第四十七期：都江堰基地扛把子回归","url":"http://tv.cntv.cn/video/VSET100284428835/16456a7943be474498e21b8b8f33f952","ptime":"2017-05-15 15:29:52","img":"http://p1.img.cctvpic.com/fmspic/2017/05/15/16456a7943be474498e21b8b8f33f952-129.jpg","len":"00:03:59","em":"CM01"},{"vsid":"VSET100284428835","order":"46","vid":"6bf3d46c0d6f4d0f9a03f1d5c25ced9b","t":"《熊猫TOP榜》 20170508 第四十六期：冰冰越狱记","url":"http://tv.cntv.cn/video/VSET100284428835/6bf3d46c0d6f4d0f9a03f1d5c25ced9b","ptime":"2017-05-08 13:37:48","img":"http://p5.img.cctvpic.com/fmspic/2017/05/08/6bf3d46c0d6f4d0f9a03f1d5c25ced9b-129.jpg","len":"00:04:22","em":"CM01"},{"vsid":"VSET100284428835","order":"45","vid":"76d6ece3031d4e09894da13dea0384c3","t":"《熊猫TOP榜》 20170501 第四十五期：城池坚守之战","url":"http://tv.cntv.cn/video/VSET100284428835/76d6ece3031d4e09894da13dea0384c3","ptime":"2017-05-01 13:47:50","img":"http://p3.img.cctvpic.com/fmspic/2017/05/01/76d6ece3031d4e09894da13dea0384c3-109.jpg","len":"00:03:19","em":"CM01"},{"vsid":"VSET100284428835","order":"44","vid":"d7d835cfc9cb4002afd849ca1c2324ea","t":"《熊猫TOP榜》 20170424 第四十四期：掏出手机 搞事情","url":"http://tv.cntv.cn/video/VSET100284428835/d7d835cfc9cb4002afd849ca1c2324ea","ptime":"2017-04-24 14:52:17","img":"http://p5.img.cctvpic.com/fmspic/2017/04/24/d7d835cfc9cb4002afd849ca1c2324ea-129.jpg","len":"00:03:47","em":"CM01"},{"vsid":"VSET100284428835","order":"43","vid":"4953e2cf834846bb841da7d33edbd4df","t":"《熊猫TOP榜》 20170417 第四十三期：网红也有真材实料","url":"http://tv.cntv.cn/video/VSET100284428835/4953e2cf834846bb841da7d33edbd4df","ptime":"2017-04-17 12:06:37","img":"http://p4.img.cctvpic.com/fmspic/2017/04/17/4953e2cf834846bb841da7d33edbd4df-129.jpg","len":"00:04:13","em":"CM01"},{"vsid":"VSET100284428835","order":"42","vid":"9a568a7a99914e2aabfcb92a33d1b432","t":"《熊猫TOP榜》 20170410 第四十二期：上古神器在此 谁敢放肆","url":"http://tv.cntv.cn/video/VSET100284428835/9a568a7a99914e2aabfcb92a33d1b432","ptime":"2017-04-10 14:45:24","img":"http://p2.img.cctvpic.com/fmspic/2017/04/10/9a568a7a99914e2aabfcb92a33d1b432-129.jpg","len":"00:03:37","em":"CM01"}]
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
         * 0 : {"vsid":"VSET100284428835","relvsid":"","name":"熊猫TOP榜","img":"http://p1.img.cctvpic.com/photoAlbum/vms/standard/img/2016/8/5/VSETvxHANBMkCKTqyPatfdBz160805.jpg","enname":"其他","url":"http://tv.cntv.cn/videoset/VSET100284428835","cd":"","zy":"","bj":"","dy":"","js":"","nf":"","yz":"","fl":"","sbsj":"","sbpd":"其他","desc":"这是一档每周五更新的盘点类节目，每周盘点近期最萌、最酷、最搞笑的直播精彩画面","playdesc":"","zcr":"","fcl":""}
         * count : 62
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
             * vsid : VSET100284428835
             * relvsid :
             * name : 熊猫TOP榜
             * img : http://p1.img.cctvpic.com/photoAlbum/vms/standard/img/2016/8/5/VSETvxHANBMkCKTqyPatfdBz160805.jpg
             * enname : 其他
             * url : http://tv.cntv.cn/videoset/VSET100284428835
             * cd :
             * zy :
             * bj :
             * dy :
             * js :
             * nf :
             * yz :
             * fl :
             * sbsj :
             * sbpd : 其他
             * desc : 这是一档每周五更新的盘点类节目，每周盘点近期最萌、最酷、最搞笑的直播精彩画面
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
         * vsid : VSET100284428835
         * order : 48
         * vid : 06e4ebb0c3e14be4b2bc544807269c8b
         * t : 《熊猫TOP榜》 20170522 第四十八期：不吃苹果的熊猫不是乖滚滚
         * url : http://tv.cntv.cn/video/VSET100284428835/06e4ebb0c3e14be4b2bc544807269c8b
         * ptime : 2017-05-22 15:04:15
         * img : http://p1.img.cctvpic.com/fmspic/2017/05/22/06e4ebb0c3e14be4b2bc544807269c8b-105.jpg
         * len : 00:03:11
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
