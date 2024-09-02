package businesscard.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.enterprise.context.ApplicationScoped;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@ApplicationScoped
public class QrCodeService {

    private final String company = "Test";
    final String url = "https://visitenkarten."+company+".de/?uuid=";

    public BufferedImage generateQrCode(UUID uuid) throws WriterException {
        //Set Level of Error Correction
        Map<EncodeHintType,Object> hints = Map.of(EncodeHintType.ERROR_CORRECTION, com.google.zxing.qrcode.decoder.ErrorCorrectionLevel.H);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url +uuid.toString(), BarcodeFormat.QR_CODE, 500, 500, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    public BufferedImage getQrCodeWithImg(UUID uuid) throws IOException, WriterException {
        BufferedImage logo = ImageIO.read(new File("C:\\Users\\Loren\\Documents\\GitHub\\visitenkarten-backend\\src\\main\\resources\\sidionLogo.png"));
        BufferedImage qrcode = generateQrCode(uuid);
        BufferedImage scaledOverlay = scaleOverlay(qrcode, logo);
        int deltaHeight = qrcode.getHeight() - scaledOverlay.getHeight();
        int deltaWidth = qrcode.getWidth() - scaledOverlay.getWidth();
        BufferedImage combined = new BufferedImage(qrcode.getWidth(), qrcode.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) combined.getGraphics();
        g2.drawImage(qrcode, 0, 0,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
        g2.drawImage(scaledOverlay, deltaWidth / 2, deltaHeight / 2, null);
        //TODO Remove when testing Over
        File f = new File("C:\\Users\\Loren\\Desktop\\Test\\TestSave.png");
        ImageIO.write(combined, "PNG", f);
        return combined;
    }

    private BufferedImage scaleOverlay(BufferedImage qrcode, BufferedImage logo) throws IOException{
        //Scale the logo to 30% of the QR Code
        int scaledWidth = (int) Math.round(qrcode.getWidth() * 0.2);
        int scaledHeight = (int) Math.round(qrcode.getHeight() * 0.2);
        int scaledShadowX = (int) Math.round(qrcode.getWidth() * 0.3);
        int scaledShadowY = (int) Math.round(qrcode.getHeight() * 0.3);
        int shadowX = (int) Math.round((scaledShadowX - scaledWidth) / 2.0);
        int shadowY = (int) Math.round((scaledShadowY - scaledHeight) / 2.0);
        BufferedImage imageBuff = new BufferedImage(scaledShadowX, scaledShadowY, BufferedImage.TYPE_INT_ARGB);
        BufferedImage shadowImage = new BufferedImage(scaledShadowX, scaledShadowY, BufferedImage.TYPE_INT_ARGB);
        Graphics g = imageBuff.createGraphics();
        g.setClip(new Ellipse2D.Float(0, 0, scaledShadowX, scaledShadowX));
        g.drawImage(shadowImage, 0, 0, new Color(255, 255, 255, 255), null);
        g.drawImage(logo.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH), shadowX, shadowY, new Color(255, 255, 255, 255), null);
        g.dispose();
        return imageBuff;
    }

    public void sendQrCode() {
        //TODO Implement sending of QR Code with Email
    }
}