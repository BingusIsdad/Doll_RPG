package main;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    Clip clip;
    URL soundURL[]= new URL[30];
    public Sound(){
        soundURL[0] = getClass().getResource("/sound/Theme.wav");
        soundURL[1] = getClass().getResource("/sound/Coin.wav");
        soundURL[2] = getClass().getResource("/sound/Powerup.wav");
        soundURL[3] = getClass().getResource("/sound/Unlock.wav");
        soundURL[4] = getClass().getResource("/sound/Fanfare.wav");
    }
    public void setFile(int i){
try{
    AudioInputStream ais = Audio.System.getAudioInputStream(soundURL[i]);
    }catch(Exception e) {
}
    }
    public void play(){

    }
    public void loop(){

    }
    public void stop(){

    }
}
