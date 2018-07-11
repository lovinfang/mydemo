package com.demo.concurrent.DNdemo.collect.forkjoin.SunWuKong;

import com.demo.concurrent.DNdemo.collect.forkjoin.IPickTaoZi.IPickTaoZi;
import com.demo.concurrent.DNdemo.collect.forkjoin.IPickTaoZi.IProcessTaoZi;
import com.demo.concurrent.DNdemo.collect.forkjoin.MakePanTaoArray;
import com.demo.concurrent.DNdemo.collect.forkjoin.SunWuKong.Impl.WuKongPickImpl;
import com.demo.concurrent.DNdemo.collect.forkjoin.SunWuKong.Impl.WuKongProcessImpl;
import com.demo.concurrent.DNdemo.collect.forkjoin.vo.PanTao;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinWuKong {
    private static class XiaoWuKong extends RecursiveTask<Integer> {

        private final static int THRESHOLD = 100;//阈值，数组多小，进行具体的业务操作
        private PanTao[] src;
        private int fromIndex;
        private int toIndex;
        private IPickTaoZi pickTaoZi;

        public XiaoWuKong(PanTao[] src, int fromIndex, int toIndex, IPickTaoZi pickTaoZi) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
            this.pickTaoZi = pickTaoZi;
        }

        @Override
        protected Integer compute() {
            if (toIndex-fromIndex<THRESHOLD){
                int count =0 ;
                for(int i=fromIndex;i<toIndex;i++){
                    if (pickTaoZi.pick(src,i)) count++;
                }
                return count;
            }else{
                //fromIndex....mid......toIndex
                int mid = (fromIndex+toIndex)/2;
                XiaoWuKong left = new XiaoWuKong(src,fromIndex,mid,pickTaoZi);
                XiaoWuKong right = new XiaoWuKong(src,mid,toIndex,pickTaoZi);
                invokeAll(left,right);
                return left.join()+right.join();

            }
        }
    }

    public static void main(String[] args) {

        ForkJoinPool pool = new ForkJoinPool();
        PanTao[] src = MakePanTaoArray.makeArray();
        IProcessTaoZi processTaoZi = new WuKongProcessImpl();
        IPickTaoZi pickTaoZi = new WuKongPickImpl(processTaoZi);

        long start = System.currentTimeMillis();

        XiaoWuKong xiaoWuKong = new XiaoWuKong(src,0,
                src.length-1,pickTaoZi);

        pool.invoke(xiaoWuKong);
        //System.out.println("Task is Running.....");

        System.out.println("The count is "+ xiaoWuKong.join()
                +" spend time:"+(System.currentTimeMillis()-start)+"ms");

    }
}
