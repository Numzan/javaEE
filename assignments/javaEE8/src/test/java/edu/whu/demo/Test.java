package edu.whu.demo;

public class Test {
    public void testJWT() {
        byte[] secret = "a4664280bb05135bdfe79d62078e15347d02440a951896714f4af4e0b7eb2873d1686c1da2da0d048a03673be1e88b1fcea8b9d86a5dc49adbecb14e8b5a5da7".getBytes();
        String userName = "lzy";
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        Map<String, Object> infoMap = new HashMap<>();
        infoMap.put("roles",roles);
        String token = Jwts.builder()
                .setClaims(infoMap)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60*60))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
        System.out.println("token: "+token);
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        System.out.println("claims info: "+claims);
        System.out.println("Subject: "+ claims.getSubject());
        System.out.println("Expiration: "+ claims.getExpiration());
        System.out.println("IssuedAt: "+ claims.getIssuedAt());
        System.out.println("roles: "+ claims.get("roles"));
    }
}
