#include "Sphere.hpp"

using namespace rt;

Intersection Sphere::getIntersection(const Line& line, float minDist, float maxDist) {
	Vector dx = line.dx();
	Vector x0 = line.x0();

	//Calculate the intersection with the sphere
	//(x-xc)^2 + (y - yc)^2 + (z - zc)^2 = R^2
	double a, b, c, d, e, f;

	a = dx.x();
	b = x0.x();

	c = dx.y();
	d = x0.y();

	e = dx.z();
	f = x0.z();
	
	//Intersection with the Sphere center
	Vector center = this->center();
	double R = this->radius();

	double xc, yc, zc;
	xc = center.x();
	yc = center.y();
	zc = center.z();

	//dx^2*t^2 - 2(v0 - vc)*dx*t + (x0 - v)^2 - R^2 = 0
	double A, B, C;
	A = pow(a, 2) + pow(c, 2) + pow(e, 2);
	B = 2 * (a * b + c * d + e * f - a * xc - c * yc - e * zc);
	C = pow(b, 2) + pow(d, 2) + pow(f, 2) + pow(xc, 2) + pow(yc, 2) + pow(zc, 2)
		- 2 * (b * xc + d * yc + f * zc)
		- pow(R, 2);

	double delta = pow(B, 2) - 4 * A * C;

	if (delta < 0) {
		//Does not intersect a sphere
		return Intersection(false, this, &line, 0);
	}
	else if (delta == 0) {
		//Unique solution
		return Intersection(true, this, &line, -B / (2 * A));
	}
	else {
		//The smaller solution is closer to the camera
		double t1 = (-B + sqrt(delta)) / (2 * A);
		double t2 = (-B - sqrt(delta)) / (2 * A);
		return t1 < t2 ? Intersection(true, this, &line, t1) : Intersection(true, this, &line, t2);
	}
}

const Vector Sphere::normal(const Vector& vec) const {
    Vector n = vec - _center;
    n.normalize();
    return n;
}