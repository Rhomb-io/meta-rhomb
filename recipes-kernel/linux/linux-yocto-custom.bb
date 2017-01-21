# This file was derived from the linux-yocto-custom.bb recipe in
# oe-core.
#
# linux-yocto-custom.bb:
#
#   A yocto-bsp-generated kernel recipe that uses the linux-yocto and
#   oe-core kernel classes to apply a subset of yocto kernel
#   management to git managed kernel repositories.
#
# Warning:
#
#   Building this kernel without providing a defconfig or BSP
#   configuration will result in build or boot errors. This is not a
#   bug.
#
# Notes:
#
#   patches: patches can be merged into to the source git tree itself,
#            added via the SRC_URI, or controlled via a BSP
#            configuration.
#
#   example configuration addition:
#            SRC_URI += "file://smp.cfg"
#   example patch addition:
#            SRC_URI += "file://0001-linux-version-tweak.patch
#   example feature addition:
#            SRC_URI += "file://feature.scc"
#

inherit kernel
require recipes-kernel/linux/linux-yocto.inc

KERNEL_IMAGETYPE ?= "zImage"
DTB_FILE ?= "exynos4412-rhomb-expansion.dtb"

SRC_URI = "git://github.com/Rhomb-io/Exynos4412-4.8-Rhomb.git;protocol=git;bareclone=1;branch=${KBRANCH}"

SRC_URI += "file://defconfig"

SRC_URI += "file://rhomb.scc \
            file://rhomb.cfg \
            file://rhomb-user-config.cfg \
            file://rhomb-user-patches.scc \
           "

KBRANCH = "odroid-4.8.y"

LINUX_VERSION ?= "4.8.4"
LINUX_VERSION_EXTENSION ?= "-Rhomb"


SRCREV="${AUTOREV}"

PV = "${LINUX_VERSION}+git${SRCPV}"

COMPATIBLE_MACHINE_rhomb = "rhomb"
