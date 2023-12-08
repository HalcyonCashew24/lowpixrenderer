package net.ard.lowpixrender;

import java.awt.Graphics;

public class Mesh {

	Point[] points;
	int[] indices;
	
	Mesh(Point[] points, int[] indices) {
		try {
			if (indices.length % 3 != 0)
				throw new Exception();
				
			this.points = points;
			this.indices = indices;
		} catch (Exception e) {
			System.err.println("Error: created mesh without triangular indices (indices.length % 3 != 0)");
		}
		
	}
	
	public void draw(Graphics g) {
		try {
			if (indices == null || points == null)
				throw new Exception();
		
			for (int i = 0; i < indices.length; i += 3) {
				Point p1 = points[indices[i]].transformedPoint();
				Point p2 = points[indices[i+1]].transformedPoint();
				Point p3 = points[indices[i+2]].transformedPoint();
			
				if (p1.z > 0 && p1.z < 1 &&
					p2.z > 0 && p2.z < 1 &&
					p3.z > 0 && p3.z < 1) {
				
					g.drawPolygon(new int[] {
						(int) p1.x,
						(int) p2.x,
						(int) p3.x
					}, new int[] {
						(int) p1.y,
						(int) p2.y,
						(int) p3.y
					}, 3);
				}
			}
		} catch (Exception e) {
			System.err.println("Error: attepted to draw null mesh (indices == null || points == null)");
		}
	}
}
