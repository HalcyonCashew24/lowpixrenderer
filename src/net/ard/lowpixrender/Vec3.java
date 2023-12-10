package net.ard.lowpixrender;

public class Vec3 {
	
	static final float NEAR = 0.1f;
	static final float FAR = 100.0f;
	static final float FOV = 1.0f;
	
	static float a;
	static float f = 1.0f / (float) Math.tan(FOV / 2.0f);
	static float q = FAR / (FAR - NEAR);
	static float width;
	static float height;
	
	public static void setAspect(int width, int height) {
		a = (float) height / (float) width;
		Vec3.width = width;
		Vec3.height = height;
	}

	float x;
	float y;
	float z;
	
	Vec3(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	Vec3() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Vec3 project() {
		float w = z;
		
		x *= f * a;
		y *= f;
		z = z * q - NEAR * q;
		
		if (w != 0) {
			x /= w;
			y /= w;
			z /= w;
		}
		
		return this;
	}
	
	public Vec3 projected() {
		return clone().project();
	}
	
	public Vec3 screen() {
		x = (x + 1) * (width / 2.0f);
		y = (1 - y) * (height / 2.0f);
		return this;
	}
	
	public Vec3 screened() {
		return clone().screen();
	}
	
	public Vec3 scale(float sx, float sy, float sz) {		
		x *= sx;
		y *= sy;
		z *= sz;
		return this;
	}
	
	public Vec3 scale(float s) {
		x *= s;
		y *= s;
		z *= s;
		return this;
	}
	
	public Vec3 scale(Vec3 scale) {
		return scale(scale.x, scale.y, scale.z);
	}
	
	public Vec3 scaled(float sx, float sy, float sz) {
		return clone().scale(sx, sy, sz);
	}
	
	public Vec3 scaled(float s) {
		return clone().scale(s);
	}
	
	public Vec3 scaled(Vec3 scale) {
		return clone().scale(scale);
	}
	
	public Vec3 translate(float px, float py, float pz) {		
		x += px;
		y += py;
		z += pz;
		
		return this;
	}
	
	public Vec3 translate(Vec3 pos) {
		return translate(pos.x, pos.y, pos.z);
	}
	
	public Vec3 translated(float px, float py, float pz) {
		return clone().translate(px, py, pz);
	}
	
	public Vec3 translated(Vec3 pos) {
		return clone().translate(pos);
	}
	
	private Vec3 rotateX(float rx) {
		float sin = (float) Math.sin(rx);
		float cos = (float) Math.cos(rx);
		float y = this.y;
		float z = this.z;
		this.y = y * cos + z * sin;
		this.z = y * -sin + z * cos;
		return this;
	}
	
	private Vec3 rotateY(float ry) {
		float sin = (float) Math.sin(ry);
		float cos = (float) Math.cos(ry);
		float x = this.x;
		float z = this.z;
		this.x = x * cos + z * -sin;
		this.z = x * sin + z * cos;
		return this;
	}
	
	private Vec3 rotateZ(float rz) {
		float sin = (float) Math.sin(rz);
		float cos = (float) Math.cos(rz);
		float x = this.x;
		float y = this.y;
		this.x = x * cos + y * sin;
		this.y = x * -sin + y * cos;
		return this;
	}
	
	public enum Gimble {
		XYZ,
		XZY,
		YXZ,
		YZX,
		ZXY,
		ZYX
	}
	
	public Vec3 rotate(float rx, float ry, float rz, Gimble g) {		
		switch (g) {
		case XYZ: return rotateX(rx).rotateY(ry).rotateZ(rz);
		case XZY: return rotateX(rx).rotateZ(rz).rotateY(ry);
		case YXZ: return rotateY(ry).rotateX(rx).rotateZ(rz);
		case YZX: return rotateY(ry).rotateZ(rz).rotateX(rx);
		case ZXY: return rotateZ(rz).rotateX(rx).rotateY(ry);
		case ZYX: return rotateZ(rz).rotateY(ry).rotateX(rx);
		default: return this;
		}
	}
	
	public Vec3 rotate(Vec3 rot, Gimble g) {
		return rotate(rot.x, rot.y, rot.z, g);
	}
	
	public Vec3 rotate(Vec3 rot) {
		return rotate(rot.x, rot.y, rot.z, Gimble.XYZ);
	}
	
	public Vec3 rotated(float rx, float ry, float rz, Gimble g) {
		return clone().rotate(rx, ry, rz, g);
	}
	
	public Vec3 rotated(float rx, float ry, float rz) {
		return clone().rotate(rx, ry, rz, Gimble.XYZ);
	}
	
	public Vec3 rotated(Vec3 rot, Gimble g) {
		return clone().rotate(rot, g);
	}
	
	public Vec3 rotated(Vec3 rot) {
		return clone().rotate(rot);
	}
	
	public float dot(Vec3 vec) {
		return x * vec.x + y * vec.y + z * vec.z;
	}
	
	public Vec3 cross(Vec3 vec) {
		Vec3 cross = new Vec3();
		
		cross.x = y * vec.z - z * vec.y;
		cross.y = z * vec.x - x * vec.z;
		cross.z = x * vec.y - y * vec.x;
		
		return cross;
	}
	
	public Vec3 normalize(float n) {		
		float s = n / (float) Math.sqrt(x * x + y * y + z * z);
		
		if (s != (1.0f / 0.0f)) {
			x *= s;
			y *= s;
			z *= s;
		}
		return this;
	}
	
	public Vec3 normalized(float n) {
		return clone().normalize(n);
	}
	
	public Vec3 clone() {
		return new Vec3(x, y, z);
	}
}
