package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.supercsv.encoder.CsvEncoder;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

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
    public String handleFileUpload(@RequestParam("testfile") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        System.out.println("★★★test post start★★★");

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
        try {
            InputStream stream = file.getInputStream();
            Reader reader = new InputStreamReader(stream);
            ICsvListReader listReader = new CsvListReader(
                    reader,
                    CsvPreference.EXCEL_PREFERENCE);

//
//            // 一行ずつ読み込み
//            while ((line = listReader.read()) != null) {
//                // nullを空文字に置き換える
//                for (int i = 0; i < line.size(); i++)
//                    if (line.get(i) == null)
//                        line.set(i, "");
//
//                csvList.add(new ArrayList<String>(line));
//            }
            // CSVファイルを閉じる
            listReader.close();

        } catch (Exception e) {
            // 異常終了
            e.printStackTrace();
        }

        System.out.println("★★★test post end★★★");
        return "uploadForm";
    }

}
