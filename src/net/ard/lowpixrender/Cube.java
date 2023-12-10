package net.ard.lowpixrender;

public class Cube extends Mesh {
	
	Cube() {
		super(new Vec3[] {
				new Vec3(-0.5f, -0.5f, -0.5f),
				new Vec3(-0.5f,  0.5f, -0.5f),
				new Vec3( 0.5f,  0.5f, -0.5f),
				new Vec3( 0.5f, -0.5f, -0.5f),
				new Vec3(-0.5f, -0.5f,  0.5f),
				new Vec3(-0.5f,  0.5f,  0.5f),
				new Vec3( 0.5f,  0.5f,  0.5f),
				new Vec3( 0.5f, -0.5f,  0.5f)
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
	}
	
	Cube(float x, float y, float z, float sx, float sy, float sz, float rx, float ry, float rz) {
		super(new Vec3[] {
				new Vec3(-0.5f, -0.5f, -0.5f),
				new Vec3(-0.5f,  0.5f, -0.5f),
				new Vec3( 0.5f,  0.5f, -0.5f),
				new Vec3( 0.5f, -0.5f, -0.5f),
				new Vec3(-0.5f, -0.5f,  0.5f),
				new Vec3(-0.5f,  0.5f,  0.5f),
				new Vec3( 0.5f,  0.5f,  0.5f),
				new Vec3( 0.5f, -0.5f,  0.5f)
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
		
		pos = new Vec3(x, y, z);
		scale = new Vec3(sx, sy, sz);
		rot = new Vec3(rx, ry, rz);
	}
}
