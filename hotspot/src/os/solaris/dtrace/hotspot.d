
provider hotspot {
  probe class__loaded(char*, uintptr_t, void*, uintptr_t);
  probe class__unloaded(char*, uintptr_t, void*, uintptr_t);
  probe class__initialization__required(char*, uintptr_t, void*, intptr_t,int);
  probe class__initialization__recursive(char*, uintptr_t, void*, intptr_t,int);
  probe class__initialization__concurrent(char*, uintptr_t, void*, intptr_t,int);
  probe class__initialization__erroneous(char*, uintptr_t, void*, intptr_t, int);
  probe class__initialization__super__failed(char*, uintptr_t, void*, intptr_t,int);
  probe class__initialization__clinit(char*, uintptr_t, void*, intptr_t,int);
  probe class__initialization__error(char*, uintptr_t, void*, intptr_t,int);
  probe class__initialization__end(char*, uintptr_t, void*, intptr_t,int);
  probe vm__init__begin();
  probe vm__init__end();
  probe vm__shutdown();
  probe vmops__request(char*, uintptr_t, int);
  probe vmops__begin(char*, uintptr_t, int);
  probe vmops__end(char*, uintptr_t, int);
  probe gc__begin(uintptr_t);
  probe gc__end();
  probe mem__pool__gc__begin(
    char*, uintptr_t, char*, uintptr_t, 
    uintptr_t, uintptr_t, uintptr_t, uintptr_t);
  probe mem__pool__gc__end(
    char*, uintptr_t, char*, uintptr_t, 
    uintptr_t, uintptr_t, uintptr_t, uintptr_t);
  probe thread__start(char*, uintptr_t, uintptr_t, uintptr_t, uintptr_t);
  probe thread__stop(char*, uintptr_t, uintptr_t, uintptr_t, uintptr_t);
  probe thread__sleep__begin(long long);
  probe thread__sleep__end(int);
  probe thread__yield();
  probe thread__park__begin(uintptr_t, int, long long);
  probe thread__park__end(uintptr_t);
  probe thread__unpark(uintptr_t);
  probe method__compile__begin(
    char*, uintptr_t, char*, uintptr_t, char*, uintptr_t, char*, uintptr_t); 
  probe method__compile__end(
    char*, uintptr_t, char*, uintptr_t, char*, uintptr_t, 
    char*, uintptr_t, uintptr_t); 
  probe compiled__method__load(
    char*, uintptr_t, char*, uintptr_t, char*, uintptr_t, void*, uintptr_t);
  probe compiled__method__unload(
    char*, uintptr_t, char*, uintptr_t, char*, uintptr_t); 
  probe monitor__contended__enter(uintptr_t, uintptr_t, char*, uintptr_t);
  probe monitor__contended__entered(uintptr_t, uintptr_t, char*, uintptr_t);
  probe monitor__contended__exit(uintptr_t, uintptr_t, char*, uintptr_t);
  probe monitor__wait(uintptr_t, uintptr_t, char*, uintptr_t, uintptr_t);
  probe monitor__waited(uintptr_t, uintptr_t, char*, uintptr_t);
  probe monitor__notify(uintptr_t, uintptr_t, char*, uintptr_t);
  probe monitor__notifyAll(uintptr_t, uintptr_t, char*, uintptr_t);

  probe object__alloc(int, char*, uintptr_t, uintptr_t);
  probe method__entry(
    int, char*, int, char*, int, char*, int);
  probe method__return(
    int, char*, int, char*, int, char*, int);
};

#pragma D attributes Evolving/Evolving/Common provider hotspot provider
#pragma D attributes Private/Private/Unknown provider hotspot module
#pragma D attributes Private/Private/Unknown provider hotspot function
#pragma D attributes Evolving/Evolving/Common provider hotspot name
#pragma D attributes Evolving/Evolving/Common provider hotspot args
