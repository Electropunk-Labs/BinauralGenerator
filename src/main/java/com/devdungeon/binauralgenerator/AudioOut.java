/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.devdungeon.binauralgenerator;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author NanoDano <nanodano@devdungeon.com>
 */
public class AudioOut {
    
    private SourceDataLine line;
    private AudioFormat audioFormat;
    
    AudioOut(AudioFormat _audioFormat)
    {
        audioFormat = _audioFormat;
        try {
            line = AudioSystem.getSourceDataLine(audioFormat);
            line.open(audioFormat, (int)audioFormat.getSampleRate());
            line.start();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioOut.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Write one second of samples to the line.
     */ 
    public void playStereo(AudioBuffer leftChannel, AudioBuffer rightChannel) {
        // Need to interpolate these
        int length = (int)audioFormat.getSampleRate();
        for (int l = 0; l < length; l+=2) {
            line.write(leftChannel.getBuffer(), l, 2); // 2 bytes from each buffer
            line.write(rightChannel.getBuffer(), l, 2);
        }
//        
//        
//        line.write(leftChannel.getBuffer(), 0, (int)audioFormat.getSampleRate());
//        line.write(rightChannel.getBuffer(), 0, (int)audioFormat.getSampleRate());
//        line.drain();
    }
}
