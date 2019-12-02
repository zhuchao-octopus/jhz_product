package com.cy.test;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import javax.swing.*;
import java.io.*;
public class Down {
 @SuppressWarnings("deprecation")
 public static void main(String[] args) {
  final JFrame jf = new JFrame("下载程序");
  JPanel north = new JPanel();
  JLabel label = new JLabel("输入下载网址：");
  final JTextField address = new JTextField(30);
  final JTextArea showArea = new JTextArea();
  JScrollPane jsp = new JScrollPane(showArea);
  JPanel south = new JPanel();
  JButton downLoad = new JButton("下载");
  JButton clearUp = new JButton("清空");
  //north
  north.add(label);
  north.add(address);
  //south
  south.add(downLoad);
  south.add(clearUp);
  //下载按钮触发事件
  downLoad.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e){
    String str = address.getText();
    try{
     URL url = new URL(str);
     URLConnection urlCon = url.openConnection();
     final String line = System.getProperty("line.separator");
     //显示下载信息
     showArea.append("文件下载信息：");
     showArea.append(line);
     showArea.append("host :" + url.getHost());
     showArea.append(line);
     showArea.append("port :" + url.getPort());
     showArea.append(line);
     showArea.append("Contenttype :" + urlCon.getContentType());
     showArea.append(line);
     showArea.append("Contentlength :"
       + urlCon.getContentLength());
     showArea.append(line);
     //弹出"保存文件"对话框
     FileDialog fopen = new FileDialog(jf, "保存文件",
       FileDialog.SAVE);
     fopen.show();
     final String fileStr = fopen.getDirectory()
       + fopen.getFile();
     String fileStrJudge = fopen.getFile();
     //判断是否下载文件
     if (fileStrJudge != null){
      
      final FileOutputStream out = new FileOutputStream(
        fileStr);
      showArea.append(fileStr + "正在下载......");
      showArea.append(line);
      //利用线程实现文件的下载 实现文件下载的并发性 可同时下载多个文件
      final InputStream in = urlCon.getInputStream();
      Runnable r = new Runnable(){
       int data;
       public void run()
       {
        try{
         while ((data = in.read()) != -1)
          out.write(data);
         out.close();
         in.close();
         showArea.append(fileStr + "文件下载成功!");
         showArea.append(line);
        }catch (Exception ex){
         System.out.println("错误");
        }
       }
      };
      Thread t = new Thread(r);
      t.start();
     }//end-if
     else{
      showArea.append("文件下载失败!");
      showArea.append(line);
     }
    }catch (Exception ex){
     ex.getStackTrace();
    }
   }
  });
  
  //“清空”按钮触发事件
  clearUp.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e){
    showArea.setText("");
   }
   
  });
  jf.getContentPane().add(north, "North");
  jf.getContentPane().add(jsp, "Center");
  jf.getContentPane().add(south, "South");
  jf.setSize(500, 500);
  jf.setLocation(100, 100);
  jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
  jf.show();
 }
}