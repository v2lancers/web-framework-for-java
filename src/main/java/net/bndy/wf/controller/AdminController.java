/*******************************************************************************
 * Copyright (C) 2017 http://bndy.net
 * Created by Bendy (Bing Zhang)
 ******************************************************************************/
package net.bndy.wf.controller;

import org.aspectj.util.FileUtil;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController extends _BaseController {

    @RequestMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("locale", LocaleContextHolder.getLocale().toString());

        File rootModule = new File(this.getClass().getResource("/static/apps/admin/modules").getFile());

        List<String> jsFiles = new ArrayList<>();
        for (File f : FileUtil.listFiles(rootModule,
            (pathname) -> pathname.getName().toLowerCase().endsWith(".js"))) {

            jsFiles.add(f.getPath().replace( new File(this.getClass().getResource("/").getFile()).getPath(), "/")
                // fix path separator issue on WinOS
                .replace("\\", "/")
                .replace("//", "/")
            );
        }
        model.addAttribute("moduleJsFiles", jsFiles);

        List<String> modules = new ArrayList<>();
        for (File f: FileUtil.listFiles(rootModule, (pathname -> pathname.getName().toLowerCase().endsWith(".html")))) {
            String moduleName = f.getPath()
                .replace(".html", "")
                .replace(
                    new File(this.getClass().getResource("/static/apps/admin/modules").getFile()).getPath(), "")
                .replaceAll("[\\\\/]+", "-")
                .replaceAll("^-", "")
                ;

            modules.add(moduleName);
        }
        model.addAttribute("modules", modules);

        return "admin/index";
    }
}
