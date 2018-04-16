package com.zzz.study.netty;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by zha on 2018/4/8.
 */
public class Demo2 {

    public static void main(String[] args) throws IOException {
        BufferedImage image = ImageIO.read(new File("C:\\Users\\zha\\Pictures/222.png"));
        for (int y = 0; y <image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if(image.getRGB(x,y) == -1){
                    System.out.print("灌");
                }else{
                    System.out.print("水");
                }

            }
            System.out.println();
        }
    }
}
