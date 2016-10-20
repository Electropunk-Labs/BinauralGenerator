/*
 * Copyright (C) 2016 NanoDano <nanodano@devdungeon.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
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
public class AudioOut extends Thread {
    
    private SourceDataLine line;
    private AudioFormat audioFormat;
    private byte[] leftBuffer;
    private byte[] rightBuffer;
    
    
    AudioOut(AudioFormat _audioFormat, byte[] _leftBuffer, byte[] _rightBuffer)
    {
        if (_audioFormat.getChannels() != 2 ) {
            Logger.getLogger(AudioOut.class.getName()).log(Level.SEVERE, null, "AudioOut only supports 2 channel(stereo) audio formats.");
        }
        leftBuffer = _leftBuffer;
        rightBuffer = _rightBuffer;
        audioFormat = _audioFormat;
        try {
            line = AudioSystem.getSourceDataLine(audioFormat);
            line.open(audioFormat, (int)audioFormat.getSampleRate());
            line.start();
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioOut.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    @Override
    public void run() {
        int samplePosition = 0;
        int endPosition = (int)audioFormat.getSampleRate();
        
        while (!this.isInterrupted()) {
            line.write(leftBuffer, samplePosition, 2); // 2 bytes from each buffer
            line.write(rightBuffer, samplePosition, 2);
            samplePosition += 2;
            if (samplePosition >= endPosition) {
                samplePosition = 0;
            }
        }
    }
    
    public void shutdown() {
        this.interrupt();
        line.flush();
        line.close();
    }
}
