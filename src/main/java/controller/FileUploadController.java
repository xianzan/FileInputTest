package controller;

import java.io.*;
import java.util.List;

import entity.Process;
import entity.RequestBody;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import util.HttpUtils;
import util.PackJson;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileUploadController {

    /**
     * 获取file.html页面
     */
    @RequestMapping("/file")
    public String file() {
        return "file";
    }

    /**
     * 获取upfile.html页面
     */
    @RequestMapping("/upfile")
    public String upfile() {
        return "upfile";
    }

    /**
     * 获取mutifile.html页面
     */
    @RequestMapping("/mutifile")
    public String multifile() {
        return "mutifile";
    }

    /**
     * 单个文件上传具体实现方法;
     *
     * @param file
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
              /*
               * 文件上传到指定目录；
               *
               */
                System.out.println(file.getOriginalFilename());
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File("C:\\test\\" + file.getOriginalFilename())));

                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败," + e.getMessage();
            }
            return "上传成功";
        } else {
            return "上传失败，因为文件是空的.";
        }
    }

    /**
     * 多文件上传处理类
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    private String upload(MultipartHttpServletRequest request) throws IllegalStateException, IOException {
        List<MultipartFile> fileList = request.getFiles("files");

        //上传的地址
        String path = "C:\\work\\tmp\\sample";
        if (fileList != null && fileList.size() > 0) {
            for (MultipartFile file : fileList) {
                // 取得上传文件
                if (file != null) {
                    // 取得当前上传文件的文件名称
                    String FileName = file.getOriginalFilename();

                    int size = (int) file.getSize();
                    System.out.println(FileName + "-->" + size);
                    // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (FileName.trim() != "") {

                        // 重命名上传后的文件名
                        String fileName = file.getOriginalFilename();

                        //组装json request 请求
                        String[] user_list={"sample_user"};

                        Process process = new Process();
                        process.setFolder_path_type("path");
                        process.setFolder_path_value("Document");
                        process.setEntry_name(FileName);
                        process.setExt_id(FileName.substring(0,(FileName.length()-4))+"_123.pdf");
                        process.setEntry_file_path(path+"\\"+fileName);

                        RequestBody requestBody = new RequestBody();
                        requestBody.setUser_list(user_list);
                        requestBody.setPriority("normal");
                        requestBody.setProcess(process);
                        String json = PackJson.setjson(requestBody);


                        //测试服务器的Ip地址
                        String url = "http://localhost:8080/document/put";

                        System.out.println("请求实体："+json);
                        HttpUtils.sendHttpPostRequest(url,json);
                        File dest = new File(path + "\\" + fileName);
                        if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                            dest.getParentFile().mkdir();
                        }
                        try {
                            //将文件写入指定目录下s
                            file.transferTo(dest);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            return "文件上传失败";
                        }

                    }
                }
            }
        }
        return "文件上传成功";
    }


    /**
     * 多文件具体上传，主要是使用了MultipartHttpServletRequest和MultipartFile
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "multifileUpload", method = RequestMethod.POST)
    @ResponseBody
    public String multifileUpload(HttpServletRequest request) {
        System.out.println("开始上传");

        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
        System.out.println(files);
        String path = "C:\\work\\tmp\\sample";
      //  uploadLocalMedia(path,request);
        System.out.println("方法执行结束");
        if (files.isEmpty()) {
            System.out.println("这是空文件");
            return "false";
        }

        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            if (file.isEmpty()) {
                return "false";
            } else {
                File dest = new File(path + "\\" + fileName);
                if (!dest.getParentFile().exists()) { //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    return "false";
                }
            }
        }
        return "true";
    }



    /**
     * 上传媒体文件，存储在服务端
     *
     * @param path  获取文件需要上传到的路径
     * @param request 客户端请求
     * @return
     */
//    public static String uploadLocalMedia(String path,HttpServletRequest request){
//        String filename ="";
//        //获得磁盘文件条目工厂
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        //如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
//        //设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
//        /**
//         * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，
//         * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
//         * 然后再将其真正写到 对应目录的硬盘上
//         */
//        factory.setRepository(new File(path));
//        //设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
//        factory.setSizeThreshold(1024*1024) ;
//        //高水平的API文件上传处理
//        ServletFileUpload upload = new ServletFileUpload(factory);
//
//        try {
//            //可以上传多个文件
//            List<FileItem> list = (List<FileItem>)upload.parseRequest(new ServletRequestContext(request));
//            System.out.println("开始遍历");
//            System.out.println("list:"+list.size());
//            System.out.println("list:"+list.isEmpty());
//            for(FileItem item : list)  {
//                //如果获取的 表单信息是普通的 文本 信息
//                if(item.isFormField())  {
//                    //获取用户具体输入的字符串,因为表单提交过来的是 字符串类型的
//                    String value = item.getString() ;
//                } else{
//                    //对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，视频这些
//                    /**
//                     * 以下三步，主要获取 上传文件的名字
//                     */
//                    //获取路径名
//                    String value = item.getName() ;
//                    //索引到最后一个反斜杠
//                    int start = value.lastIndexOf("\\");
//
//                    //截取 上传文件的 字符串名字，加1是 去掉反斜杠，
//                    filename = value.substring(start+1);
//                    System.out.println("filename="+ filename);
//                    //真正写到磁盘上
//                    //它抛出的异常 用exception 捕捉
//                    //item.write( new File(path,filename) );//第三方提供的
//                    //手动写的
//                    OutputStream out = new FileOutputStream(new File(path,filename));
//                    InputStream in = item.getInputStream() ;
//                    int length = 0 ;
//                    byte [] buf = new byte[1024] ;
//                    // in.read(buf) 每次读到的数据存放在   buf 数组中
//                    while( (length = in.read(buf) ) != -1)  {
//                        //在   buf 数组中 取出数据 写到 （输出流）磁盘上
//                        out.write(buf, 0, length);
//                    }
//                    in.close();
//                    out.close();
//                }
//            }
//        } catch (FileUploadException e) {
//          System.out.println("文件上传异常");
//        } catch (Exception e) {
//            System.out.println("文件处理IO异常");
//        }
//        return  filename ;
//    }
}
