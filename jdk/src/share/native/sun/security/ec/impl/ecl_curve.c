#include "ecl.h"
#include "ecl-curve.h"
#include "ecl-priv.h"
#ifndef _KERNEL
#include <stdlib.h>
#include <string.h>
#endif

#define CHECK(func) if ((func) == NULL) { res = 0; goto CLEANUP; }

/* Duplicates an ECCurveParams */
ECCurveParams *
ECCurveParams_dup(const ECCurveParams * params, int kmflag)
{
        int res = 1;
        ECCurveParams *ret = NULL;

#ifdef _KERNEL
        ret = (ECCurveParams *) kmem_zalloc(sizeof(ECCurveParams), kmflag);
#else
        CHECK(ret = (ECCurveParams *) calloc(1, sizeof(ECCurveParams)));
#endif
        if (params->text != NULL) {
#ifdef _KERNEL
                ret->text = kmem_alloc(strlen(params->text) + 1, kmflag);
                bcopy(params->text, ret->text, strlen(params->text) + 1);
#else
                CHECK(ret->text = strdup(params->text));
#endif
        }
        ret->field = params->field;
        ret->size = params->size;
        if (params->irr != NULL) {
#ifdef _KERNEL
                ret->irr = kmem_alloc(strlen(params->irr) + 1, kmflag);
                bcopy(params->irr, ret->irr, strlen(params->irr) + 1);
#else
                CHECK(ret->irr = strdup(params->irr));
#endif
        }
        if (params->curvea != NULL) {
#ifdef _KERNEL
                ret->curvea = kmem_alloc(strlen(params->curvea) + 1, kmflag);
                bcopy(params->curvea, ret->curvea, strlen(params->curvea) + 1);
#else
                CHECK(ret->curvea = strdup(params->curvea));
#endif
        }
        if (params->curveb != NULL) {
#ifdef _KERNEL
                ret->curveb = kmem_alloc(strlen(params->curveb) + 1, kmflag);
                bcopy(params->curveb, ret->curveb, strlen(params->curveb) + 1);
#else
                CHECK(ret->curveb = strdup(params->curveb));
#endif
        }
        if (params->genx != NULL) {
#ifdef _KERNEL
                ret->genx = kmem_alloc(strlen(params->genx) + 1, kmflag);
                bcopy(params->genx, ret->genx, strlen(params->genx) + 1);
#else
                CHECK(ret->genx = strdup(params->genx));
#endif
        }
        if (params->geny != NULL) {
#ifdef _KERNEL
                ret->geny = kmem_alloc(strlen(params->geny) + 1, kmflag);
                bcopy(params->geny, ret->geny, strlen(params->geny) + 1);
#else
                CHECK(ret->geny = strdup(params->geny));
#endif
        }
        if (params->order != NULL) {
#ifdef _KERNEL
                ret->order = kmem_alloc(strlen(params->order) + 1, kmflag);
                bcopy(params->order, ret->order, strlen(params->order) + 1);
#else
                CHECK(ret->order = strdup(params->order));
#endif
        }
        ret->cofactor = params->cofactor;

  CLEANUP:
        if (res != 1) {
                EC_FreeCurveParams(ret);
                return NULL;
        }
        return ret;
}

#undef CHECK

/* Construct ECCurveParams from an ECCurveName */
ECCurveParams *
EC_GetNamedCurveParams(const ECCurveName name, int kmflag)
{
        if ((name <= ECCurve_noName) || (ECCurve_pastLastCurve <= name) ||
                                        (ecCurve_map[name] == NULL)) {
                return NULL;
        } else {
                return ECCurveParams_dup(ecCurve_map[name], kmflag);
        }
}

/* Free the memory allocated (if any) to an ECCurveParams object. */
void
EC_FreeCurveParams(ECCurveParams * params)
{
        if (params == NULL)
                return;
        if (params->text != NULL)
#ifdef _KERNEL
                kmem_free(params->text, strlen(params->text) + 1);
#else
                free(params->text);
#endif
        if (params->irr != NULL)
#ifdef _KERNEL
                kmem_free(params->irr, strlen(params->irr) + 1);
#else
                free(params->irr);
#endif
        if (params->curvea != NULL)
#ifdef _KERNEL
                kmem_free(params->curvea, strlen(params->curvea) + 1);
#else
                free(params->curvea);
#endif
        if (params->curveb != NULL)
#ifdef _KERNEL
                kmem_free(params->curveb, strlen(params->curveb) + 1);
#else
                free(params->curveb);
#endif
        if (params->genx != NULL)
#ifdef _KERNEL
                kmem_free(params->genx, strlen(params->genx) + 1);
#else
                free(params->genx);
#endif
        if (params->geny != NULL)
#ifdef _KERNEL
                kmem_free(params->geny, strlen(params->geny) + 1);
#else
                free(params->geny);
#endif
        if (params->order != NULL)
#ifdef _KERNEL
                kmem_free(params->order, strlen(params->order) + 1);
#else
                free(params->order);
#endif
#ifdef _KERNEL
        kmem_free(params, sizeof(ECCurveParams));
#else
        free(params);
#endif
}
