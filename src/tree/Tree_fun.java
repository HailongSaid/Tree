package tree;
import java.applet.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;
import javax.swing.JOptionPane;

class Node{
    public int p;
    public int left,right;
    public int data;
    int x,y;
    Node(int data){
        p = -1;
        left = -1;
        right = -1;
        this.data = data;
    }
}
public class Tree_fun {
    int dy = 40;
    int dx = 40;
    Vector pv = new Vector();
    Node get(Vector pv,int i){
        return (Node)(pv.elementAt(i));
    }
    
    boolean seach_Tree(int i,int e,int no){
        if(pv.isEmpty()||i==-1){
            return false;
        }
        boolean f1 = true;
        boolean f2 = true;
        if(get(pv,i).data>e)
             f1 = seach_Tree(get(pv,i).left,e,no);
        else if(get(pv,i).data<e)
             f2 = seach_Tree(get(pv,i).right,e,no);
        else{
            no = i;
            return true;
        }
        return f1||f2;
    }
    
     boolean min_Tree(int i,int min){
         if(i==-1||pv.isEmpty())
             return false;
         else{
            int m1 = get(pv,i).data;
            int m2 = m1;
            if(get(pv,i).left!=-1)
                min_Tree(get(pv,i).left,m1);
            else if(get(pv,i).right!=-1)
                min_Tree(get(pv,i).right,m2);
            int mm = m1>m2?m2:m1;
            min = mm>get(pv,i).data?get(pv,i).data:mm;
            return true;
        }
         
     }
     boolean max_Tree(int i,int max){
        if(i==-1||pv.isEmpty())
            return false;
        int m1 = get(pv,i).data;
        int m2 = m1;
        if(get(pv,i).left!=-1)
            max_Tree(get(pv,i).left,m1);
        else if(get(pv,i).right!=-1)
            max_Tree(get(pv,i).right,m2);
        int mm = m1<m2?m2:m1;
        max = mm>get(pv,i).data?mm:get(pv,i).data;
        return true;
        }
    int insert_Tree(int data){   //向树中插入数据
        Node a = new Node(data);
        if(pv.isEmpty()){
            a.x = 200;
            a.y = 20;
            pv.addElement(a);
            return 0;
        }
        int p = 0;
        int i = -1,j = 0;
        while(p!=-1){
            if(get(pv,p).data>data){
                i = get(pv,p).left;
                j = -1;
            }
            else if(get(pv,p).data<data){
                i = get(pv,p).right;
                j = 1;
            }
            else{
                j = 0;
                JOptionPane.showMessageDialog(null,  "当前树中已经有这个数据","提示", JOptionPane.WARNING_MESSAGE);            
                return p;
            }
            if(i!=-1)
                p = i;
            else 
                break;
        }
        if(j==-1){
            get(pv,p).left = pv.size();
            a.p = p;
        }
        else{
             get(pv,p).right = pv.size();
            a.p = p;
        }
        pv.addElement(a);         
        return pv.size()-1;
    }
    boolean walk_Tree(int i){    //遍历树
        if(pv.isEmpty()||i==-1){
            return false;
        }
        if(i==0){
            get(pv,0).x = 200;
            get(pv,0).y = 20;
        }
        if(get(pv,i).left!=-1){
            get(pv,get(pv,i).left).x = get(pv,i).x-dx;
            get(pv,get(pv,i).left).y = get(pv,i).y+dy;
            walk_Tree(get(pv,i).left);
        }
        System.out.print(get(pv,i).data+"  ");
       if(get(pv,i).right!=-1){
           get(pv,get(pv,i).right).x = get(pv,i).x+dx;
           get(pv,get(pv,i).right).y = get(pv,i).y+dy;
           walk_Tree(get(pv,i).right);
       }
       return true;
    }
     int seach_Tree1(int i,int e){  //查找元素e对应的号码
        if(pv.isEmpty()||i==-1){
            return -1;
        }
        int no = -1;
        if(get(pv,i).data>e)
             no = seach_Tree1(get(pv,i).left,e);
        else if(get(pv,i).data<e)
             no = seach_Tree1(get(pv,i).right,e);
        else
            no = i;
        return no;
    }
    
    int min_Tree1(int i){     //找出最小元素对应的号码
         if(i==-1||pv.isEmpty())
             return -1;
         if(get(pv,i).left!=-1)
             return min_Tree1(get(pv,i).left);
         else 
             return i;
         
   }
    
    int max_Tree1(int i){   //找出最大元素对应的号码
         if(i==-1||pv.isEmpty())
             return -1;
         if(get(pv,i).right!=-1)
             return max_Tree1(get(pv,i).right);
         else
             return i;
      
   }
    void delete_Tree(int e){      //删除数据e
        int no = seach_Tree1(0,e);
        if(no==-1){
            JOptionPane.showMessageDialog(null,  "当前树中没有这个数据","提示", JOptionPane.WARNING_MESSAGE);            
            return;
        }
        
        if(get(pv,no).left==-1){
            if(get(pv,no).right!=-1)
                get(pv,get(pv,no).right).p = get(pv,no).p;
            if(get(pv,get(pv,no).p).data>e)
                get(pv,get(pv,no).p).left = get(pv,no).right;
            else
                get(pv,get(pv,no).p).right = get(pv,no).right;
            get(pv,no).x = 0;
            get(pv,no).y = 0;
        }
        else if(get(pv,no).right==-1){
            get(pv,get(pv,no).left).p = get(pv,no).p;
            if(get(pv,get(pv,no).p).data>e)
                get(pv,get(pv,no).p).left = get(pv,no).left;
            else
                get(pv,get(pv,no).p).right = get(pv,no).left;
            get(pv,no).x = 0;
            get(pv,no).y = 0;
        }
        else{
            int min = min_Tree1(get(pv,no).right);
            int data = get(pv,min).data;
            delete_Tree(data);
            get(pv,no).data = data;
        }  
    }
    int precursor_Tree(int e){
        int no = seach_Tree1(0,e);
         if(no==-1){
            JOptionPane.showMessageDialog(null,  "当前树中没有这个数据","提示", JOptionPane.WARNING_MESSAGE);            
            return -1;
        }
        if(get(pv,no).left!=-1){
            return max_Tree1(get(pv,no).left);
        }
        else{
            int pp = get(pv,no).p;
            while(pp!=-1&&get(pv,pp).data>get(pv,no).data){
                no =pp;
                pp = get(pv,no).p;
            }
            return pp;
        }
    }
     int Successor_Tree(int e){
         int no = seach_Tree1(0,e);
         if(no==-1){
            JOptionPane.showMessageDialog(null,  "当前树中没有这个数据","提示", JOptionPane.WARNING_MESSAGE);            
            return -1;
        }
        if(get(pv,no).right!=-1){
            return min_Tree1(get(pv,no).right);
        }
        else{
            int pp = get(pv,no).p;
            while(pp!=-1&&get(pv,pp).data<get(pv,no).data){
                no =pp;
                pp = get(pv,no).p;
            }
            return pp;
        }
       
    }
}
