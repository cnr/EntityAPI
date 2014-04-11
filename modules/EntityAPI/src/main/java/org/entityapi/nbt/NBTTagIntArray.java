/*
 * This file is part of EntityAPI.
 *
 * EntityAPI is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EntityAPI is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with EntityAPI.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.entityapi.nbt;

import org.entityapi.exceptions.NBTReadException;
import org.entityapi.exceptions.NBTWriteException;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class NBTTagIntArray extends NBTReturnable<int[]> {

    private int[] data;

    public NBTTagIntArray() {
    }

    public NBTTagIntArray(int[] i) {
        this.data = i;
    }

    @Override
    public byte getTypeId() {
        return 11;
    }

    @Override
    public int[] getValue() {
        return this.data;
    }

    @Override
    public String toString() {
        String s = "[";
        int[] aint = this.data;
        int i = aint.length;

        for (int j = 0; j < i; ++j) {
            int k = aint[j];

            s = s + k + ",";
        }

        return s + "]";
    }

    @Override
    public boolean equals(Object object) {
        return super.equals(object) ? Arrays.equals(this.data, ((NBTTagIntArray) object).data) : false;
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ Arrays.hashCode(this.data);
    }

    @Override
    void write(DataOutput dataoutput) {
        try {
            dataoutput.writeInt(this.data.length);
            for (int i = 0; i < this.data.length; ++i) {
                dataoutput.writeInt(this.data[i]);
            }
        } catch (IOException e) {
            throw new NBTWriteException(e);
        }
    }

    @Override
    void load(DataInput datainput, int depth) {
        try {
            int j = datainput.readInt();
            this.data = new int[j];
            for (int k = 0; k < j; ++k) {
                this.data[k] = datainput.readInt();
            }
        } catch (IOException e) {
            throw new NBTReadException(e);
        }
    }

    @Override
    public NBTBase clone() {
        int[] aint = new int[this.data.length];

        System.arraycopy(this.data, 0, aint, 0, this.data.length);
        return new NBTTagIntArray(aint);
    }
}