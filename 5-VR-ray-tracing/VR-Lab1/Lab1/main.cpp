#include <cmath>
#include <iostream>
#include <string>

#include "Vector.hpp"
#include "Line.hpp"
#include "Geometry.hpp"
#include "Sphere.hpp"
#include "Image.hpp"
#include "Color.hpp"
#include "Intersection.hpp"
#include "Material.hpp"

#include "Scene.hpp"

using namespace std;
using namespace rt;

float imageToViewPlane(int n, int imgSize, float viewPlaneSize) {
    float u = (float)n*viewPlaneSize / (float)imgSize;
    u -= viewPlaneSize / 2;
    return u;
}

const Intersection findFirstIntersection(const Line& ray,
    float minDist, float maxDist) {
    Intersection intersection;

	//For each sphere, check if it intersects with the line
    for (int i = 0; i < geometryCount; i++) {
        Intersection in = scene[i]->getIntersection(ray, minDist, maxDist);
        if (in.valid()) {	//WUT
            if (!intersection.valid()) {
                intersection = in;
            }
            else if (in.t() < intersection.t()) {
                intersection = in;
            }
        }
    }

    return intersection;
}

int main() {
    Vector viewPoint(0, 0, 0);
    Vector viewDirection(0, 0, 1);
    Vector viewUp(0, -1, 0);

    float frontPlaneDist = 0;
    float backPlaneDist = 1000;

    float viewPlaneDist = 512;
    float viewPlaneWidth = 1024;
    float viewPlaneHeight = 768;

    int imageWidth = 1024;
    int imageHeight = 768;

    Vector viewParallel = viewUp^viewDirection;	

    viewDirection.normalize();
    viewUp.normalize();
    viewParallel.normalize();

    Image image(imageWidth, imageHeight);

	//ADD CODE HERE

	Vector x0 = viewPoint;
	for (int i = 0; i < imageWidth; i++)
	{
		for (int j = 0; j < imageHeight; j++)
		{
			Vector x1 = viewPoint + viewDirection * viewPlaneDist +
				viewParallel * imageToViewPlane(i, imageWidth, viewPlaneWidth) +
				viewUp * imageToViewPlane(j, imageHeight, viewPlaneHeight);

			Intersection intersection = findFirstIntersection(Line(x0, x1, false), frontPlaneDist, backPlaneDist);
			
			//Set ligthing here
			Color color = Color(0, 0, 0);
			if (intersection.geometry() != NULL) {
				Vector V = intersection.vec();	//Intersection position vector
				Material material = intersection.geometry()->material(); 

				for (int k = 0; k < lightCount; k++)
				{
					Light* light = lights[k]; 
					Vector L = light->position(); //Light position vector
					Vector C = viewPoint;	//Camera position vector
					Vector E = C - V; //Vector from the intersectio point to the camera - normalized
					E.normalize();
					Vector N = intersection.geometry()->normal(V); //Normal to the surface at the intersection point
					N.normalize();
					Vector T = L - V; //Vector from the intersection of the light - normalized
					T.normalize();
					Vector R = N * (N * T) * 2 - T;
					
					color += material.ambient() * light->ambient();

					if (N * T > 0)
					{
						color += material.diffuse() * light->diffuse() * (N * T);
					}

					if (E * R > 0)
					{
						color += material.specular() * light->specular() * pow(E * R, material.shininess());
					}
					color *= light->intensity();

					//if(color.red() != 0 || color.green() != 0 || color.blue() != 0)
					//	cout << color.red() << color.green() << color.blue();
				}
				image.setPixel(i, j, color);
			}	
			else {
				image.setPixel(i, j, color); //toBlack
			}
		}
	}

	//CLOSE CODE HERE

    image.store("scene.png");

    for (int i = 0; i < geometryCount; i++) {
        delete scene[i];
    }

    return 0;
}
