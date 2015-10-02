package no.bjornadal.pizzabakeren.rest.controller;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**pk
 * Created by andreasb on 02.10.15.
 */
@RestController
public class ApkController {

    @RequestMapping("/apk")
    public void downloadApk(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.android.package-archive");
        response.setHeader("Content-Disposition", "attachment; filename=pizzabakeren.apk");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("pizzabakeren.apk").getFile());

        response.getOutputStream().write(FileUtils.readFileToByteArray(file));
    }
}
