package com.tutu359.community.entity;

/**
 * 封装分页相关的信息
 */
public class Page {

    //当前页码
    private int current = 1;
    //每页显示条目数
    private int limit = 10;
    //总条目数
    private int rows;
    //查询路径(用于复用分页链接)
    private String path;


    /**
     * 计算并返回起始下标
     * @return
     */
    public int getOffset(){
        return (current-1)*limit;
    }

    /**
     *  计算并返回总页数
     * @return
     */
    public int getTotal(){
        if(rows%limit==0){
            return rows/limit;
        }else {
            return rows/limit+1;        }
    }

    /**
     * 计算并返回起始页码
     *
     * @return
     */
    public int getFrom() {

        if(getCurrent()<=3){//前三页的起始页码都是1
            return 1;
        }else if(getCurrent()>3 && getCurrent()<=getTotal()-2){
            return getCurrent()-2;
        }else if((getTotal()-getCurrent())<3){//后三页起始页都是最终页-5
            return getTotal()-5;
        }else {
            return 1;
        }

        /*int from = current-2;
        return from >0 ? from : 1;*/
    }

    /**
     * 计算并返回结束页码
     * @return
     */
    public int getTo(){

        if(getCurrent()<=3){//前三页最后一页都是5
            return 5;
        }else if(getCurrent()>3 && getCurrent()<=getTotal()-2){
            return getCurrent()+2;
        }else if((getTotal()-getCurrent())<3){//后三页最后一页都是最终页
            return getTotal();
        }else {
            return 5;
        }


        /*int total = getTotal();
        int to = current+2;
        return to>total? total : to;*/


    }


    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current>=1){
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit>=1 && limit<=100){
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows>0){
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }




}
