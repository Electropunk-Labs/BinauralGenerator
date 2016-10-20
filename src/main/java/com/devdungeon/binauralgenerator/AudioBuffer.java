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

/**
 *
 * @author NanoDano <nanodano@devdungeon.com>
 */
public final class AudioBuffer
{    
    private final byte[] buffer;
    private final int sampleRate;
    
    AudioBuffer(int _sampleRate, int _frequency)
    {
        sampleRate = _sampleRate;
        buffer = new byte[sampleRate];
        
        // Default to a sine wave
        fillBufferWithSineWave(_frequency);
    }
    
    /**
     * Fill the buffer with one sine wave.
     * @param frequency
     */
    public void fillBufferWithSineWave(int frequency)
    {
        //buffer = ToneGenerator.generateSineWave(sampleRate, frequency, buffer)
        double period = (double)sampleRate / frequency;
        for (int i = 0; i < buffer.length; i++)
        {
            double angleLeft = 2.0 * Math.PI * i / period;
            buffer[i] = (byte) (Math.sin(angleLeft) * 127f);
        }
    }

    public byte[] getBuffer()
    {
        return buffer;
    }
            
}
