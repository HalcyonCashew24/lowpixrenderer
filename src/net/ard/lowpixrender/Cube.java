package net.ard.lowpixrender;

public class Cube extends Mesh {

	Point pos;
	
	Cube(float x, float y, float z) {
		super(new Point[] {
				new Point(-0.5f + x, -0.5f + y, -0.5f + z),
				new Point(-0.5f + x,  0.5f + y, -0.5f + z),
				new Point( 0.5f + x,  0.5f + y, -0.5f + z),
				new Point( 0.5f + x, -0.5f + y, -0.5f + z),
				new Point(-0.5f + x, -0.5f + y,  0.5f + z),
				new Point(-0.5f + x,  0.5f + y,  0.5f + z),
				new Point( 0.5f + x,  0.5f + y,  0.5f + z),
				new Point( 0.5f + x, -0.5f + y,  0.5f + z)
		}, new int[] {
				0, 1, 2,
				0, 2, 3,
				3, 2, 6,
				3, 6, 7,
				7, 6, 5,
				7, 5, 4,
				4, 5, 1,
				4, 1, 0,
				1, 5, 6,
				1, 6, 2,
				4, 0, 3,
				4, 3, 7
		});
		
		pos = new Point(x, y, z);
	}
}
