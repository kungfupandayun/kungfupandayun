#include "sysapi.h"

int pmsg2(void *arg)
{
        int fid1 = (int)arg;

        printf(" 3");
        assert(psend(fid1, 6) == 0);
        assert(psend(fid1, 7) == 457);
        return 1;
}
