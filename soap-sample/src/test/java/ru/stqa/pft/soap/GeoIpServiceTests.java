package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GeoIpServiceTests {

   @Test
   public void testMaIP() {
      String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getLocation();
              //getIpLocation("194.28.29.152");
      String[] list;
      list = ipLocation.split(">");
      list = list[2].split("<");

      Assert.assertEquals(list[0], "DE");
   }
}