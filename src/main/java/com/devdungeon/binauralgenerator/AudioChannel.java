/*
 * The MIT License
 *
 * Copyright 2016 NanoDano <nanodano@devdungeon.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.devdungeon.binauralgenerator;

/**
 *
 * @author NanoDano <nanodano@devdungeon.com>
 */
public final class AudioChannel
{    
    private final byte[] buffer;
    private final int sampleRate;
    
    AudioChannel(int _sampleRate, int _frequency)
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
    
    // sineWave
    // squareWave
    // whiteNoise
    public byte[] getBuffer()
    {
        return buffer;
    }
            
}
