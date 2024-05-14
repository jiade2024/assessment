package com.jd.assessment.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Jiade.Xu
 * @Date 2024/5/11 10:20
 */
@Data
@Component
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadProperty {

    private String storePath = "/usr/local/files/assessment/";

    /*
    * other fields like maxSingleFileSize, max count per upload request, ...
    * */
}
