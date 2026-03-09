/*
 * Copyright (c) 2010-2026. Institut Pasteur.
 *
 * This file is part of Icy.
 * Icy is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Icy is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Icy. If not, see <https://www.gnu.org/licenses/>.
 */

package plugins.adufour.quickhull;

import org.jetbrains.annotations.Contract;

import javax.vecmath.Vector3d;

/**
 * Represents vertices of the hull, as well as the points from
 * which it is formed.
 *
 * @author John E. Lloyd, Fall 2004
 */
public class Vertex {
    /**
     * Spatial point associated with this vertex.
     */
    Vector3d pnt;

    /**
     * Back index into an array.
     */
    int index;

    /**
     * List forward link.
     */
    Vertex prev;

    /**
     * List backward link.
     */
    Vertex next;

    /**
     * Current face that this vertex is outside.
     */
    Face face;

    /**
     * Constructs a vertex and sets its coordinates to 0.
     */
    @Contract(pure = true)
    public Vertex() {
        pnt = new Vector3d();
    }

    /**
     * Constructs a vertex with the specified coordinates
     * and index.
     */
    @Contract(pure = true)
    public Vertex(final double x, final double y, final double z, final int idx) {
        pnt = new Vector3d(x, y, z);
        index = idx;
    }
}
