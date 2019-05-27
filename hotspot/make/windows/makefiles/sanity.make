
!include local.make

all: checkCL checkLink

checkCL:
	@ if "$(MSC_VER)" NEQ "1310" if "$(MSC_VER)" NEQ "1399" if "$(MSC_VER)" NEQ "1400" if "$(MSC_VER)" NEQ "1500" if "$(MSC_VER)" NEQ "1600" if "$(MSC_VER)" NEQ "1700" \
	echo *** WARNING *** unrecognized cl.exe version $(MSC_VER) ($(RAW_MSC_VER)).  Use FORCE_MSC_VER to override automatic detection.

checkLink:
	@ if "$(LD_VER)" NEQ "710" if "$(LD_VER)" NEQ "800" if "$(LD_VER)" NEQ "900" if "$(LD_VER)" NEQ "1000" if "$(LD_VER)" NEQ "1100" \
	echo *** WARNING *** unrecognized link.exe version $(LD_VER) ($(RAW_LD_VER)).  Use FORCE_LD_VER to override automatic detection.
