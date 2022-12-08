package org.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.models.enums.WaitTime;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Property {
  private static final Properties configFile = new Properties();

  static {
    try {
      LoggerContext loggerContext =
          (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
      File file = new File("src/main/java/resources/log4j2.xml");
      loggerContext.setConfigLocation(file.toURI());
      String path = System.getProperty("user.dir") + "/configuration.properties";
      FileInputStream input = new FileInputStream(path);
      configFile.load(input);
      if (System.getProperty("browser") == null) {
        System.setProperty("browser", "chrome");
      }
      if (System.getProperty("environment") == null) {
        System.setProperty("environment", "QA");
      }
      input.close();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("Failed to load properties file!");
    }
  }

  /**
   * This method returns property value from configuration.properties file
   *
   * @param keyName property name
   * @return property value
   */
  public static String getProperty(String keyName) {
    return configFile.getProperty(keyName);
  }

  public static Integer getWaitTime(WaitTime waitTime) {
    switch (waitTime) {
      case VERY_SHORT:
        return Integer.valueOf(getProperty("veryshortWait"));
      case MEDIUM:
        return Integer.valueOf(getProperty("mediumWait"));
      case LONG:
        return Integer.valueOf(getProperty("longWait"));
      case VISIBILITY:
        return Integer.valueOf(getProperty("visibilityWait"));
      default:
        return Integer.valueOf(getProperty("shortWait"));
    }
  }
}
