/* *********************************************************************
 *
 * The Original Code is the MPI Arbitrary Precision Integer Arithmetic library.
 *
 * The Initial Developer of the Original Code is
 * Michael J. Fromberger.
 * Portions created by the Initial Developer are Copyright (C) 1997
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *
 *********************************************************************** */

/*  Utilities for finding and working with prime and pseudo-prime integers */

#ifndef _MP_PRIME_H
#define _MP_PRIME_H

#include "mpi.h"

extern const int prime_tab_size;   /* number of primes available */
extern const mp_digit prime_tab[];

/* Tests for divisibility    */
mp_err  mpp_divis(mp_int *a, mp_int *b);
mp_err  mpp_divis_d(mp_int *a, mp_digit d);

/* Random selection          */
mp_err  mpp_random(mp_int *a);
mp_err  mpp_random_size(mp_int *a, mp_size prec);

/* Pseudo-primality testing  */
mp_err  mpp_divis_vector(mp_int *a, const mp_digit *vec, int size, int *which);
mp_err  mpp_divis_primes(mp_int *a, mp_digit *np);
mp_err  mpp_fermat(mp_int *a, mp_digit w);
mp_err mpp_fermat_list(mp_int *a, const mp_digit *primes, mp_size nPrimes);
mp_err  mpp_pprime(mp_int *a, int nt);
mp_err mpp_sieve(mp_int *trial, const mp_digit *primes, mp_size nPrimes,
                 unsigned char *sieve, mp_size nSieve);
mp_err mpp_make_prime(mp_int *start, mp_size nBits, mp_size strong,
                      unsigned long * nTries);

#endif /* _MP_PRIME_H */
