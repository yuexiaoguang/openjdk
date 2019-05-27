#!/bin/sh
## some tests require path to find test source dir
if [ "${TESTSRC}" = "" ]
then
  TESTSRC=${PWD}
  echo "TESTSRC not set.  Using "${TESTSRC}" as default"
fi
echo "TESTSRC=${TESTSRC}"
## Adding common setup Variables for running shell tests.
. ${TESTSRC}/../../test_env.sh


set -x

cp ${TESTSRC}/Test6890943.java .
cp ${TESTSRC}/input6890943.txt .
cp ${TESTSRC}/output6890943.txt .
cp ${TESTSRC}/Test6890943.sh .

${COMPILEJAVA}/bin/javac ${TESTJAVACOPTS} -d . Test6890943.java

${TESTJAVA}/bin/java -XX:-PrintVMOptions -XX:+IgnoreUnrecognizedVMOptions ${TESTVMOPTS} Test6890943 < input6890943.txt > pretest.out 2>&1

# This test sometimes tickles an unrelated performance warning that interferes with diff.
grep -v 'warning: Performance bug: SystemDictionary' pretest.out > test.out

diff output6890943.txt test.out

result=$?
if [ $result -eq 0 ]
then
  echo "Passed"
  exit 0
else
  echo "Failed"
  exit 1
fi
