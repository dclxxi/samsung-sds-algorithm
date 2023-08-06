package _04_number_theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main3955 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            ExtendedGcd gcd = getExtendedGcd(A, B);

            if (gcd.r != 1) {
                sb.append("IMPOSSIBLE");
            } else {
                double x0 = -gcd.s;
                double y0 = gcd.t;

                long xUpper = (long) Math.ceil(x0 / B) - 1;
                long yUpper = (long) Math.ceil(y0 / A) - 1;
                long k = Math.min(xUpper, yUpper);

                long yLower = (long) Math.ceil((y0 - 1e9) / A);
                if (yLower <= k) {
                    sb.append((int) y0 - A * k);
                } else {
                    sb.append("IMPOSSIBLE");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static ExtendedGcd getExtendedGcd(long a, long b) {
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        while (r1 != 0) {
            long q = r0 / r1;

            long temp = r0 - r1 * q;
            r0 = r1;
            r1 = temp;

            temp = s0 - s1 * q;
            s0 = s1;
            s1 = temp;

            temp = t0 - t1 * q;
            t0 = t1;
            t1 = temp;
        }

        return new ExtendedGcd(s0, t0, r0);
    }

    static class ExtendedGcd {

        long s, t, r;

        ExtendedGcd(long s, long t, long r) {
            this.s = s;
            this.t = t;
            this.r = r;
        }
    }
}
