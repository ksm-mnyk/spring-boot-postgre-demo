package com.example.controller;




import org.springframework.mock.web.MockMultipartFile;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;

import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/test")
public class UploadController {
    // uploadForm表示
    @GetMapping("")
    String index() {
        System.out.println("★★★test get★★★");
        return "uploadForm";
    }
    @PostMapping("")
    public String handleFileUpload(@RequestParam("testfile") MultipartFile file1,
                                   RedirectAttributes redirectAttributes) {
        System.out.println("★★★test post start★★★");

        File file = new File("src/test/resources/input.txt");
        try (
            FileInputStream input = new FileInputStream(file);
         ) {
            MultipartFile multipartFile = new MockMultipartFile(file.getName(), input);

            // ここでサービスを呼び出すテストを記載

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));




        // ==== 文字列で取得 ====
        // ファイル名出力
        System.out.println(file.getOriginalFilename());

        if (file.isEmpty()) return "uploadForm";// ファイルなし
        try {
            // ファイルの中身をbytesで取得
            byte[] bytes = file.getBytes();
            // Stringに変換
            String csvstring = new String(bytes);
            // 読み込んだファイルをコンソールに出力
            System.out.println(csvstring);
        } catch (Exception e) {
            // 異常終了
            e.printStackTrace();
        }

        // ==== SuperCsvを使用 ====
        try (
            InputStream stream = file.getInputStream();
            Reader reader = new InputStreamReader(stream);
            ICsvListReader listReader = new CsvListReader(
                    reader, CsvPreference.EXCEL_PREFERENCE);
        ) {
            List<String> list = null;
            while ((list = listReader.read()) != null) {
                System.out.println("1列目" + list.get(0));
                System.out.println("2列目" + list.get(1));
            }
        } catch (Exception e) {
            // 異常終了
            e.printStackTrace();
        }
        System.out.println("★★★test post end★★★");
        return "uploadForm";
    }

}
