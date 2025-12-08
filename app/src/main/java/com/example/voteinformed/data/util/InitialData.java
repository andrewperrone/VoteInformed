package com.example.voteinformed.data.util;

import com.example.voteinformed.data.entity.Article;
import com.example.voteinformed.data.entity.Election;
import com.example.voteinformed.data.entity.Issue;
import com.example.voteinformed.data.entity.Politician;
import com.example.voteinformed.data.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InitialData {

    public static List<Article> getArticles() {
        List<Article> list = new ArrayList<>();

        list.add(new Article(
                "NYC launches new voter education campaign ahead of 2025 elections",
                "https://demo.voteinformed.org/articles/nyc-voter-education-2025"
        ));

        list.add(new Article(
                "Debate grows over congestion pricing in Manhattan",
                "https://demo.voteinformed.org/articles/congestion-pricing-manhattan"
        ));

        list.add(new Article(
                "City Council advances affordable housing zoning changes",
                "https://demo.voteinformed.org/articles/affordable-housing-zoning"
        ));

        list.add(new Article(
                "State lawmakers propose new climate resilience funding for coastal areas",
                "https://demo.voteinformed.org/articles/climate-resilience-coastal-ny"
        ));

        list.add(new Article(
                "Public safety reforms: balancing police accountability and community trust",
                "https://demo.voteinformed.org/articles/public-safety-reform-ny"
        ));

        list.add(new Article(
                "Subway modernization plan promises faster, more reliable service",
                "https://demo.voteinformed.org/articles/subway-modernization-plan"
        ));

        list.add(new Article(
                "NYC schools pilot mental health support program for students",
                "https://demo.voteinformed.org/articles/nyc-school-mental-health"
        ));

        list.add(new Article(
                "Small business recovery grants extended for another year",
                "https://demo.voteinformed.org/articles/small-business-recovery-grants"
        ));

        list.add(new Article(
                "Rent stabilization debate intensifies as tenants call for stronger protections",
                "https://demo.voteinformed.org/articles/rent-stabilization-debate"
        ));

        list.add(new Article(
                "Early voting locations expand across New York State",
                "https://demo.voteinformed.org/articles/early-voting-expands-ny"
        ));

        return list;
    }

    public static List<Election> getElections() {
        List<Election> list = new ArrayList<>();
        list.add(new Election("[election name]", new Date(), "[location]", "[description]"));
        return list;
    }

    public static List<Issue> getIssues() {
        List<Issue> list = new ArrayList<>();
        list.add(new Issue("Civil Rights", "[description]", "[type]", "[location]"));

        list.add(new Issue("Healthcare", "[description]", "[type]", "[location]"));

        list.add(new Issue("Infrastructure", "[description]", "[type]", "[location]"));

        list.add(new Issue("Transporation", "[description]", "[type]", "[location]"));

        list.add(new Issue("Job", "[description]", "[type]", "[location]"));

        list.add(new Issue("Economy", "[description]", "[type]", "[location]"));

        list.add(new Issue("Military", "[description]", "[type]", "[location]"));

        list.add(new Issue("Environment", "[description]", "[type]", "[location]"));

        list.add(new Issue("Crime", "[description]", "[type]", "[location]"));

        list.add(new Issue("Privacy", "[description]", "[type]", "[location]"));

        list.add(new Issue("Taxes", "[description]", "[type]", "[location]"));

        list.add(new Issue("Education", "[description]", "[type]", "[location]"));

        list.add(new Issue("Voting", "[description]", "[type]", "[location]"));

        return list;
    }

    private static final byte[] default_image = new byte[1];

    public static List<Politician> getPoliticians()
    {
        List<Politician> list = new ArrayList<>();

        list.add(new Politician(
                "Alexandria Ocasio-Cortez",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Alexandria_Ocasio-Cortez_Official_Portrait.jpg/500px-Alexandria_Ocasio-Cortez_Official_Portrait.jpg",
                "202-225-3965",
                "Born October 13, 1989, in The Bronx, New York. Ocasio-Cortez earned degrees from Boston University. In 2018, she defeated an incumbent in a major upset and became the youngest woman ever elected to the U.S. Congress. She is a progressive leader within the Democratic Party.",
                "250 Cannon House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Eric Adams",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fd/Eric_Adams_at_City_Hall_2023_%283x4_cropped%29.jpg/500px-Eric_Adams_at_City_Hall_2023_%283x4_cropped%29.jpg",
                "Email: mayoreric@cityhall.nyc.gov",
                "Eric Adams graduated from Bayside High School. Adams earned a master's degree in public administration from Marist College and degrees from New York City Technical College and the John Jay College of Criminal Justice. His career experience includes working as a captain with the New York Police Department. Adams founded 100 Blacks in Law Enforcement Who Care",
                "Mayor Eric Adams\nCity Hall\nNew York, NY 10007"
        ));

        list.add(new Politician(
                "Charles E. Schumer",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/Chuck_Schumer_official_photo_%283x4_cropped%29.jpg/500px-Chuck_Schumer_official_photo_%283x4_cropped%29.jpg",
                "202-224-6542",
                "Born November 23, 1950, in Brooklyn, New York. Schumer graduated from Harvard College and Harvard Law School. He served in the New York State Assembly and later in the U.S. House of Representatives from 1981 to 1999. Since 1999, he has served as U.S. Senator from New York. He became Senate Minority Leader in 2017 and Senate Majority Leader in 2021.",
                "322 Hart Senate Office Building\nWashington, DC 20510"
        ));

        list.add(new Politician(
                "Kathy Hochul",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/2/25/Kathy_Hochul_March_2024.jpg/500px-Kathy_Hochul_March_2024.jpg",
                "518-474-8390",
                "Hochul was born in Buffalo, New York. She received a B.A. from Syracuse University in 1980 and a J.D. from Catholic University in 1983. In addition to working as an attorney in private practice, she was an aide to Rep. John LaFalce (D) from 1984 to 1986 and to Sen. Daniel Patrick Moynihan (D) from 1986 to 1988. She served as Deputy Clerk of Erie County in New York from 2003 to 2007 and as Clerk of Erie County from 2007 to 2011.",
                "The Honorable Kathy Hochul\nGovernor of New York State\nNYS State Capitol Building\nAlbany, NY 12224"
        ));

        list.add(new Politician(
                "Kirsten Gillibrand",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9a/Kirsten_Gillibrand%2C_official_photo%2C_116th_Congress.jpg/500px-Kirsten_Gillibrand%2C_official_photo%2C_116th_Congress.jpg",
                "DC Office Phone: (202) 224-4451 \n Official Website Contact: https://www.gillibrand.senate.gov/contact",
                "Kirsten Elizabeth Gillibrand (born 1966) is the junior U.S. Senator from New York, serving since 2009. She previously represented New York's 20th congressional district in the House. She is known for her advocacy on military justice and sexual assault reforms.",
                "U.S. Senator for New York \n D.C. Address: 478 Russell Senate Office Building, Washington, DC 20510"
        ));

        list.add(new Politician(
                "Paul David Tonko",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4a/Paul_Tonko_118th_Congress.jpeg/250px-Paul_Tonko_118th_Congress.jpeg",
                "(202) 225-5076",
                "Paul David Tonko (born June 18, 1949) is the U.S. representative for New York's 20th congressional district. Previously representing the 21st district, he is a longtime Democratic legislator known for progressive policy positions and representing Albany, Saratoga, and Schenectady counties following 2020 redistricting.",
                "Representative Paul D. Tonko\n2269 Rayburn House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Elise Marie Stefanik",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Elise_Stefanik%2C_115th_official_photo_%284x5_cropped%29.jpg/500px-Elise_Stefanik%2C_115th_official_photo_%284x5_cropped%29.jpg",
                "(518) 561-2324",
                "Elise Marie Stefanik (born July 2, 1984) has served as the U.S. representative for New York's 21st congressional district since 2015. She was chair of the House Republican Conference from 2021 to 2025, making her the fourth-ranking House Republican during that period.",
                "Representative Elise Stefanik\n137 Margaret Street, Suite 100\nPlattsburgh, NY 12901"
        ));

        list.add(new Politician(
                "John W. Mannion",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/John_Mannion%2C_official_portrait_%28119th_Congress%29.jpg/960px-John_Mannion%2C_official_portrait_%28119th_Congress%29.jpg",
                "(202) 225-3701",
                "John W. Mannion (born July 8, 1968) is an American educator and politician serving as the U.S. representative for New York's 22nd congressional district since 2025. He previously served as a New York State senator from the 50th district between 2020 and 2024 and worked as a high school biology teacher before entering politics.",
                "Representative John W. Mannion\n1516 Longworth House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Nicholas A. Langworthy",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/7/71/Rep._Langworthy_Headshot_118.jpeg/500px-Rep._Langworthy_Headshot_118.jpeg",
                "(202) 225-3161",
                "Nicholas A. Langworthy (born February 27, 1981) is the U.S. Representative for New York's 23rd congressional district since January 3, 2023. A native of Jamestown, NY and a graduate of Niagara University, he previously served as chair of the New York Republican State Committee and as chair of the Erie County Republican Committee. He also founded a polling firm and has a background in political organizing and campaign management.",
                "Representative Nicholas A. Langworthy\n422 Cannon House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Claudia Tenney",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1f/Rep._Claudia_Tenney_official_congressional_photo.jpg/500px-Rep._Claudia_Tenney_official_congressional_photo.jpg",
                "(202) 225-3665",
                "Claudia Tenney (born February 4, 1961) is the U.S. Representative for New York’s 24th congressional district (2023–present). She previously represented the 22nd district from 2017–2019 and 2021–2023, and before that served in the New York State Assembly from 2011–2016. She is an attorney by training and a member of the Republican Party.",
                "Representative Claudia Tenney\n2230 Rayburn House Office Building\nWashington, DC 20515"
        ));
// need more change below
        list.add(new Politician(
                "Joseph D. Morelle",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6e/Joseph_Morelle_official_portrait_118th_Congress.jpg/250px-Joseph_Morelle_official_portrait_118th_Congress.jpg",
                "(202) 225-3615",
                "Joseph D. Morelle (born April 29, 1957) is the U.S. representative for New York’s 25th congressional district (since 2018). A Democrat and former New York State Assembly Majority Leader and Monroe County legislator, he represents Monroe County and part of Ontario County — centered on Rochester and surrounding suburbs.",
                "Representative Joseph D. Morelle\n570 Cannon House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Timothy M. Kennedy",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/4/42/Tim_Kennedy_118th_Congress_official_portrait.jpg",
                "(202) 225-3306",
                "Timothy M. Kennedy (born October 20, 1976) is the U.S. representative for New York’s 26th congressional district, elected in 2024 after serving in the New York State Senate from 2011 to 2024. A former occupational therapist from Buffalo, he has championed infrastructure, transit, and social-justice legislation throughout his career.",
                "Representative Timothy M. Kennedy\n423 Cannon House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Anthony H. Palumbo",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9b/Anthony_Palumbo_2021.jpg/250px-Anthony_Palumbo_2021.jpg",
                "(631) 461-9100",
                "Anthony H. Palumbo (born September 14, 1970) is a New York State Senator representing the 1st Senate District since January 1, 2021. A former Suffolk County Assistant District Attorney and member of the New York State Assembly (2013–2020), he is known for his advocacy on public safety, water quality, housing affordability, and efforts to protect Long Island’s environment and small-town character.",
                "Senator Anthony H. Palumbo\n400 W. Main Street, Suite 201\nRiverhead, NY 11901"
        ));

        list.add(new Politician(
                "Mario R. Mattera",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/3/3a/Mario_Mattera_2022.jpg",
                "631-361-2154",
                "Mario R. Mattera (born August 18, 1963) is a member of the New York State Senate, representing the 2nd district since 2021. A longtime union leader with Plumbers Local 200 and a resident of Smithtown/St. James, he has advocated for labor and local-community interests on behalf of his constituents.",
                "Senator Mario R. Mattera\n180 East Main Street, Suite 210\nSmithtown, NY 11787"
        ));

        list.add(new Politician(
                "Dean L. Murray",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8e/Dean_Murray_2023.jpg/220px-Dean_Murray_2023.jpg",
                "631-360-3356",
                "Dean L. Murray (born July 22, 1964) is a New York State Senator representing the 3rd Senate District (since 2023). Previously, he served in the New York State Assembly (2010–2012, 2015–2018), and before politics he worked in radio/TV news and founded a small-business advertising firm.",
                "Senator Dean Murray\n90-B West Main Street\nPatchogue, NY 11772"
        ));

        list.add(new Politician(
                "Monica R. Martinez",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Monica_Martinez_2023_photo.jpg",
                "(518) 455-2765",
                "Monica R. Martinez (born August 13, 1977) is a Salvadoran-American educator and politician serving as a New York State Senator for the 4th district. A former Suffolk County Legislator and assistant principal in Brentwood, NY, she has championed education, public safety, animal welfare, and support for working families. She chairs the Senate Committee on Local Government.",
                "Senator Monica R. Martinez\nLegislative Office Building, Room 903\nAlbany, NY 12247\n\nDistrict Office:\n250 Veterans Highway, State Office Building, Room 3B-41-42\nHauppauge, NY 11788"
        ));

        list.add(new Politician(
                "Steven D. Rhoads",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/8/8d/Steven_D._Rhoads_official_portrait.png",
                "518-455-3161",
                "Steven D. Rhoads is the New York State Senator representing the 5th District since January 1, 2023. A former Nassau County Legislator and longtime volunteer firefighter and attorney for first responders, he focuses on public safety, property-tax relief, and support for law enforcement.",
                "Senator Steven D. Rhoads\nRoom 513, Legislative Office Building\nAlbany, NY 12247\n\nDistrict Office:\n2900 Hempstead Turnpike, Suite 201\nLevittown, NY 11756"
        ));

        list.add(new Politician(
                "Siela A. Bynoe",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Siela_Bynoe_2025_official_portrait.jpg/250px-Siela_Bynoe_2025_official_portrait.jpg",
                "(516) 739-1700",
                "Siela A. Bynoe (born 1967/1968) is a New York State Senator representing the 6th district since January 1, 2025. A Long Island resident and former Nassau County Legislator, she is the first Black person to represent Long Island in the State Senate. A strong advocate for housing, education, and environmental issues, she previously served on the Westbury School Board and in the Nassau County Legislature.",
                "Senator Siela A. Bynoe\n65 Hilton Avenue, Suite 230\nGarden City, NY 11530"
        ));

        list.add(new Politician(
                "Jack M. Martins",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/7/7c/Jack_Martins_2024.jpg",
                "(518) 455-2677",
                "Jack M. Martins (born June 19, 1967) is a New York State Senator representing the 7th district. A former mayor of Mineola and attorney, he returned to the State Senate in 2023 after previously serving from 2011 to 2016. He has focused on fiscal discipline, local-government reform, and representing Long Island’s suburban interests.",
                "Senator Jack M. Martins\nRoom 608, Legislative Office Building\nAlbany, NY 12247\n\nDistrict Office:\n173 Mineola Boulevard, Suite 201\nMineola, NY 11501"
        ));

        list.add(new Politician(
                "Alexis Weik",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/0/0c/Alexis_Weik_official_state_senate_photo_2023.jpg",
                "(631) 665-2311",
                "Alexis Weik (born September 17, 1972) is the New York State Senator for the 8th district, serving since 2023; she initially represented the 3rd district beginning in 2021. A lifelong Long Island resident raised in Ronkonkoma and Oakdale, she earned a BBA from Dowling College and previously served as Receiver of Taxes for the Town of Islip (2012–2020). As Senator, she emphasizes support for law enforcement, small business, fiscal restraint and public safety.",
                "Senator Alexis Weik\n1 Corporate Drive, Suite GL-005\nBohemia, NY 11716"
        ));

        list.add(new Politician(
                "Patricia M. Canzoneri-Fitzpatrick",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Patricia_Canzoneri-Fitzpatrick_2025.jpg/250px-Patricia_Canzoneri-Fitzpatrick_2025.jpg",
                "(516) 766-8383",
                "Patricia M. Canzoneri-Fitzpatrick (born 1964/1965) is a member of the New York State Senate representing the 9th District since 2023. An attorney and small-business owner from Malverne, she previously served as a village trustee, Police Commissioner, Fire Commissioner, Deputy Mayor, and Budget Director in the Village of Malverne, and now focuses on public safety, fiscal responsibility, and constituent services for Nassau County.",
                "Senator Patricia M. Canzoneri-Fitzpatrick\n265 E. Merrick Road, Suite 101\nValley Stream, NY 11580"
        ));

        list.add(new Politician(
                "James Sanders Jr.",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/James_Sanders_Jr_official_photo.jpg/250px-James_Sanders_Jr_official_photo.jpg",
                "718-523-3069",
                "James Sanders Jr. (born August 14, 1957) is a New York State Senator representing the 10th district since 2013. A Marine veteran and longtime public servant from Far Rockaway, Queens, he previously served on the New York City Council and advocates for economic equity, social justice, and community development. He chairs the Senate Committee on Banks.",
                "Senator James Sanders Jr.\n142-01 Rockaway Boulevard\nSouth Ozone Park, NY 11436"
        ));

        list.add(new Politician(
                "Toby Ann Stavisky",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9f/Toby_Ann_Stavisky_2019_portrait.jpg/250px-Toby_Ann_Stavisky_2019_portrait.jpg",
                "(518) 455-3461",
                "Toby Ann Stavisky (born June 26, 1939) is a member of the New York State Senate representing the 11th district (serving since 1999). She chairs the Senate Higher Education Committee and has a long career in public service after previously working as a social-studies teacher.",
                "Senator Toby Ann Stavisky\nAlbany Office: 172 State Street, Capitol Building, Room 430\nAlbany, NY 12247\n\nDistrict Office: 134-01 20th Avenue, 2nd Floor\nCollege Point, NY 11356"
        ));

        list.add(new Politician(
                "Michael N. Gianaris",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/0/07/Michael_N._Gianaris.jpeg\n",
                "(518) 455-3486",
                "Michael N. Gianaris (born April 23, 1970) is a New York State Senator representing the 12th Senate District since 2011. He has served as Deputy Majority Leader of the New York State Senate since 2019. A former Assembly member (2001–2010), Gianaris is an attorney from Queens and a longtime advocate for tenants’ rights, criminal justice reform, and responsible economic development.",
                "Senator Michael N. Gianaris\nCapitol Building, Room 427\nAlbany, NY 12247\n\nDistrict Office:\n31-19 Newtown Avenue, Suite 402\nAstoria, NY 11102"
        ));

        list.add(new Politician(
                "Jessica Ramos",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Jessica_Ramos_2023.jpg/250px-Jessica_Ramos_2023.jpg",
                "(718) 205-3881",
                "Jessica Ramos (born June 27, 1985) is a New York State Senator representing District 13 (Queens) since 2019. A daughter of Colombian immigrants, she was raised in Astoria, Queens, and previously worked as communications director for Latino media at New York City Hall. She has made labor rights, tenants’ rights, and immigrant advocacy cornerstones of her political platform.",
                "Senator Jessica Ramos\n74-09 37th Avenue, Suite 302\nJackson Heights, NY 11372"
        ));

        list.add(new Politician(
                "Leroy G. Comrie, Jr.",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/8b/Leroy_Comrie_official_portrait.jpg/250px-Leroy_Comrie_official_portrait.jpg",
                "(718) 765-6359",
                "Leroy G. Comrie, Jr. (born August 10, 1958) is a New York State Senator representing the 14th District since January 1, 2015. A former New York City Council Member (2002–2013), he represents Queens neighborhoods including Jamaica, Hollis, Kew Gardens Hills, St. Albans and others, and emphasizes investments in education, housing, and community development.",
                "Senator Leroy G. Comrie\nDistrict Office: 113-43 Farmers Boulevard\nSt. Albans, NY 11412\nAlbany Office: Legislative Office Building, Room 913\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Joseph P. Addabbo Jr.",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Joseph_Addabbo_Jr_2025.jpg/250px-Joseph_Addabbo_Jr_2025.jpg",
                "(718) 738-1111",
                "Joseph P. Addabbo Jr. (born May 13, 1964) is a New York State Senator representing the 15th District (Queens) since 2009. A Democrat and former New York City Council member, he chairs the Senate Racing, Gaming and Wagering Committee and represents neighborhoods including Forest Hills, Woodhaven, Ozone Park, Glendale, Rego Park, Richmond Hill, Middle Village, and surrounding areas.",
                "Senator Joseph P. Addabbo Jr.\n84-16 Jamaica Avenue\nWoodhaven, NY 11421\n\nAlbany Office:\n188 State Street, Room 811\nLegislative Office Building\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "John C. Liu",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/John_Liu_portrait_2023.jpg/250px-John_Liu_portrait_2023.jpg",
                "718-765-6675",
                "John C. Liu (born January 8, 1967 in Taiwan) is a New York State Senator representing the 16th district (northeast Queens). He previously served as New York City Comptroller (2010–2013) and as New York City Councilmember (2002–2009), and is known for his work on municipal finance, education, immigrant rights, and fiscal transparency.",
                "Senator John C. Liu\nRoom 915, Legislative Office Building\nAlbany, NY 12247\n\nDistrict Office:\n38-50 Bell Boulevard, Suite C\nBayside, NY 11361"
        ));

        list.add(new Politician(
                "Steve Chan",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/5/5b/Steve_Chan_2025.jpg",
                "(718) 333-0311",
                "Steve Chan (born May 13, 1966) is a New York State Senator representing District 17 (since January 1, 2025). A former U.S. Marine, NYPD sergeant, small-business owner and Brooklyn resident since childhood, he is the first Asian-American Republican elected to the New York State Senate — winning a seat in South Brooklyn after defeating the incumbent in 2024. He advocates for public safety, education support, and fiscal responsibility.",
                "Senator Steve Chan\n6605 Fort Hamilton Parkway\nBrooklyn, NY 11219\n\nAlbany Office:\nLegislative Office Building, Room 615\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Julia Salazar",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6e/Julia_Salazar_2019.jpg/250px-Julia_Salazar_2019.jpg",
                "(718) 573-1726",
                "Julia Salazar (born December 30, 1990) is the New York State Senator for the 18th district (since 2019). A former community organizer and member of the Democratic Socialists of America, she represents much of northern Brooklyn — including Bushwick — and has advocated strongly for tenant protections, criminal-justice reform, climate justice, and social equity. She previously worked with grassroots organizations such as Jews for Racial & Economic Justice before her election.",
                "Senator Julia Salazar\nDistrict Office:\n212 Evergreen Avenue\nBrooklyn, NY 11221\n\nAlbany Office:\nRoom 514, State Capitol Building\n172 State Street\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Antonio Delgado",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/9/97/Antonio_Delgado_Lt._Governor.jpg",
                "518-474-8390",
                "Born April 16, 1954, in Mineola, New York. DiNapoli attended Hofstra University and Long Island University. He served in the New York State Assembly for over 20 years, representing Great Neck beginning at age 24. In 2007, he was appointed New York State Comptroller. As Comptroller, he oversees the state’s $250+ billion pension fund and serves as New York’s chief fiscal officer.",
                "Executive Offices Capitol\nAlbany NY 12224"
        ));

        list.add(new Politician(
                "Thomas P. DiNapoli",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/3/3c/Thomas_DiNapoli.jpg",
                "518-474-4044",
                "New York State Comptroller since 2007. Previously served in the New York State Assembly for 20 years. Oversees the state's financial operations and pension fund.",
                "110 State Street\nAlbany NY 12207"
        ));

        list.add(new Politician(
                "Letitia A. James",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/9/9f/Letitia_James.jpg",
                "1-800-771-7755",
                "Born October 18, 1958, in Brooklyn, New York. James earned her B.A. from Lehman College and her J.D. from Howard University School of Law. She began her career as a public defender and later served on the New York City Council. From 2013 to 2018, she served as New York City Public Advocate. In 2018, she was elected Attorney General of New York, becoming the first woman and first African American to hold the office.",
                "Executive Offices Capitol\nAlbany NY 12224"
        ));

        list.add(new Politician(
                "Nicholas J. LaLota",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/0/03/Nick_LaLota_118th_Congress.jpg",
                "202-225-3826",
                "Born June 23, 1978. LaLota served as a U.S. Navy officer and intelligence officer. He later worked as Chief of Staff to the Suffolk County Legislature. In 2022, he was elected to represent New York’s 1st Congressional District. His priorities include veterans’ affairs, national security, and Long Island infrastructure.",
                "122 Cannon House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Andrew R. Garbarino",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/0/0c/Andrew_Garbarino_117th_Congress.jpg",
                "202-225-7896",
                "Born September 27, 1984, in New York. Garbarino earned a law degree from Touro College. He served in the New York State Assembly from 2013 to 2020 representing Long Island. In 2020, he was elected to the U.S. House of Representatives for New York’s 2nd Congressional District.",
                "2344 Rayburn House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Thomas R. Suozzi",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/Tom_Suozzi_official_portrait_118th_Congress.jpg/800px-Tom_Suozzi_official_portrait_118th_Congress.jpg",
                "202-225-3335",
                "Born August 31, 1962, in Glen Cove, New York. Suozzi served as Mayor of Glen Cove and later as Nassau County Executive from 2002 to 2009. He served in Congress from 2017 to 2023 and returned to Congress in 2024 after winning a special election. He is known for bipartisan problem-solving and economic policy.",
                "203 Cannon House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Laura A. Gillen",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/8/87/Laura_Gillen_119th_Congress.jpg/800px-Laura_Gillen_119th_Congress.jpg",
                "202-225-5516",
                "Gillen served as Hempstead Town Supervisor from 2018 to 2020. She focused on local infrastructure, environmental protection, and municipal reform. She was elected to Congress representing New York’s 4th District and advocates for coastal protection, housing reform, and public safety.",
                "428 Cannon House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Gregory W. Meeks",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/2/2f/Gregory_Meeks_113th_Congress.jpg",
                "202-225-3461",
                "Born September 25, 1953, in Harlem, New York. Meeks earned degrees from Adelphi University and Howard University School of Law. He has served in the U.S. House of Representatives since 1998. He previously served as Chair of the House Foreign Affairs Committee.",
                "2310 Rayburn House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Grace Meng",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/6/61/Grace_Meng_113th_Congress.jpg",
                "202-225-2601",
                "Born March 28, 1953, in Puerto Rico. Velázquez holds a Ph.D. in Political Science. She has represented parts of Brooklyn and Manhattan in Congress since 1993. She was the first Puerto Rican woman elected to Congress and is known for her work on small business policy.",
                "2468 Rayburn House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Nydia M. Velazquez",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/e/eb/Nydia_Velazquez%2C_official_photo%2C_113th_Congress.jpg",
                "202-225-2361",
                "Born August 4, 1970, in Brooklyn, New York. Jeffries earned degrees from Binghamton University, Georgetown University, and NYU School of Law. He served in the New York State Assembly before being elected to Congress in 2012. In 2023, he became House Minority Leader.",
                "2302 Rayburn House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Hakeem S. Jeffries",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/4/4c/Hakeem_Jeffries_117th_Congress.jpg",
                "202-225-5936",
                "Born November 21, 1964, in Brooklyn, New York. Clarke previously served on the New York City Council. She has represented New York’s 9th District in Congress since 2007, focusing on immigration reform and technology policy.",
                "2267 Rayburn House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Yvette D. Clarke",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/1/16/Yvette_Clarke%2C_Official_Photograph%2C_113th_Congress.jpg",
                "202-225-6231",
                "Born February 26, 1976. Goldman served as a federal prosecutor in the Southern District of New York. He was lead counsel for House Democrats during the first impeachment trial of President Donald Trump. He was elected to Congress in 2022 representing New York’s 10th District.",
                "2058 Rayburn House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Daniel Goldman",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Daniel_Goldman%2C_Official_Portrait%2C_118th_Congress.jpg/800px-Daniel_Goldman%2C_Official_Portrait%2C_118th_Congress.jpg",
                "202-225-7944",
                "Born November 11, 1980, in New York City. Malliotakis served in the New York State Assembly from 2011 to 2020. In 2020, she was elected to Congress representing Staten Island and parts of Brooklyn.",
                "245 Cannon House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Nicole Malliotakis",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/3/3f/Nicole_Malliotakis_official_photo.jpg",
                "202-225-3371",
                "Born November 11, 1980, in New York City. Malliotakis served in the New York State Assembly from 2011 to 2020. In 2020, she was elected to Congress representing Staten Island and parts of Brooklyn.",
                "1124 Longworth House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Jerrold L. Nadler",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/1/1a/Jerrold_Nadler%2C_official_photo%2C_113th_Congress.jpg",
                "202-225-5635",
                "Born June 13, 1947, in Brooklyn, New York. Nadler earned his law degree from Fordham University. He has served in Congress since 1992. He was Chairman of the House Judiciary Committee during the first impeachment of Donald Trump.",
                "2132 Rayburn House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Adriano Espaillat",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/3/33/Adriano_Espaillat_official_photo.jpg",
                "202-225-4365",
                "Born September 27, 1954, in the Dominican Republic. He immigrated to the U.S. as a child. Espaillat served in the New York State Senate before being elected to Congress in 2016. He became the first Dominican-American member of Congress.",
                "2332 Rayburn House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Ritchie Torres",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/1/1b/Ritchie_Torres_117th_Congress.jpg",
                "202-225-4361",
                "Born November 22, 1953. Latimer served in the New York State Assembly, State Senate, and as Westchester County Executive from 2018 to 2024 before being elected to Congress.",
                "1414 Longworth House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "George S. Latimer",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c5/George_Latimer_2022.jpg/800px-George_Latimer_2022.jpg",
                "202-225-2464",
                "Born November 22, 1953. Latimer served in the New York State Assembly, State Senate, and as Westchester County Executive from 2018 to 2024 before being elected to Congress.",
                "1507 Longworth House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Mike Lawler",
                "Republican",
                "https://upload.wikimedia.org/wikipedia/commons/6/65/Mike_Lawler_118th_Congress.jpg",
                "202-225-6506",
                "Born September 8, 1986. Lawler served in the New York State Assembly before being elected to Congress in 2022. He represents parts of Rockland and Westchester counties.",
                "324 Cannon House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Pat Ryan",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/8/8b/Pat_Ryan_117th_Congress.jpg",
                "202-225-5614",
                "Born March 20, 1982. Ryan served as Ulster County Executive and previously worked in veterans’ services. He won a special election in 2022 to enter Congress.",
                "1708 Longworth House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "Josh Riley",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/Josh_Riley_119th_Congress.jpg/800px-Josh_Riley_119th_Congress.jpg",
                "202-225-5441",
                "Riley is an attorney and former aide to Senate Majority Leader Chuck Schumer. He focuses on rural economic development, agriculture, healthcare, and infrastructure in the Hudson Valley and Southern Tier.",
                "128 Cannon House Office Building\nWashington, DC 20515"
        ));

        list.add(new Politician(
                "James N. Tedisco",
                "Republican",
                "https://en.wikipedia.org/wiki/File:Tedisco_Headshot.jpeg",
                "518-455-2181",
                "James Nicholas Tedisco (born July 15, 1950) is a Republican politician representing New York’s 44th State Senate District and a former longtime member of the New York State Assembly. He earned a B.A. in psychology from Union College, where he was a standout basketball player, and later received a graduate degree in special education from the College of Saint Rose. Before his legislative career, he worked in education as a guidance counselor, special education teacher, athletic director, and basketball coach at high schools in the Capital Region.",
                "Senator James N. Tedisco\nNew York State Senate\nLegislative Office Building, Room 515\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Daniel G. Stec",
                "Republican",
                "https://en.wikipedia.org/wiki/File:(05-05-25)_NYS_Senator_Dan_Stec_(cropped).jpg",
                "518-455-2811",
                "Daniel George Stec (born February 21, 1969) is a Republican politician serving New York's 45th State Senate District, covering Clinton, Essex, Franklin, Warren, parts of St. Lawrence, and Washington Counties. Raised in Queensbury, New York, he earned a B.S. in chemical engineering from Clarkson University and an M.B.A. from the University of Rhode Island. A U.S. Navy veteran who worked as a nuclear engineer, he served in the New York State Assembly from 2013 to 2020 before winning election to the Senate in 2020.",
                "Senator Daniel G. Stec\nNew York State Senate\nLegislative Office Building, Room 408\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Patricia A. Fahy",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:Patricia_Fahy_2025.jpg",
                "518-455-2401",
                "Patricia Fahy (born July 1958) is a Democratic politician representing New York's 46th State Senate District, including Albany, parts of Schenectady and Montgomery Counties. She previously served 12 years in the New York State Assembly for the 109th District before winning election to the Senate in 2024.",
                "Senator Patricia A. Fahy\nNew York State Senate\nLegislative Office Building, Room 327\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Brad Hoylman-Sigal",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:(06-06-19)_NY_State_Senator_Brad_Hoylman_during_Senate_Session_at_the_NY_State_Capitol,_Albany_NY_(cropped).jpg",
                "518-455-2431",
                "Brad Madison Hoylman-Sigal (born October 27, 1965) is a Democratic State Senator for New York's 47th District covering Manhattan's west side since 2013. He serves as chair of the Senate Judiciary Committee and was elected Manhattan Borough President in 2025.",
                "Senator Brad Hoylman-Sigal\nNew York State Senate\nLegislative Office Building, Room 411\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Rachel May",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:(05-18-22)_NYS_Senator_Rachel_May_(cropped).jpg",
                "518-455-2523",
                "Rachel May is a Democratic State Senator representing New York's 48th District including Syracuse since 2019. An academic and former university administrator, she defeated incumbent David Valesky in the 2018 Democratic primary.",
                "Senator Rachel May\nNew York State Senate\nLegislative Office Building, Room 310\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Mark C. Walczyk",
                "Republican",
                "https://en.wikipedia.org/wiki/File:Mark_Walczyk_Image.jpg",
                "518-455-2381",
                "Mark C. Walczyk (born July 3, 1985) is a Republican State Senator for New York's 49th District since 2023. A U.S. Army Reserve officer and former Assemblyman, he succeeded retiring Sen. Patty Ritchie after serving Watertown City Council.",
                "Senator Mark C. Walczyk\nNew York State Senate\nLegislative Office Building, Room 330\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Christopher J. Ryan",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:Chris_Ryan_2025.jpg",
                "518-455-2500",
                "Christopher Ryan is a Democratic State Senator for New York's 50th District since 2025. Former Onondaga County Legislature minority leader and CWA Local 1123 president, he succeeded John Mannion.",
                "Senator Christopher J. Ryan\nNew York State Senate\nLegislative Office Building, Room 412\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Peter Oberacker",
                "Republican",
                "https://en.wikipedia.org/wiki/File:Peter_Oberacker_2021.jpg",
                "518-455-2921",
                "Peter Karl Oberacker Jr. (born May 13, 1963) is a Republican State Senator for New York's 51st District since 2021. A businessman from Schenevus, he succeeded retiring Sen. James Seward.",
                "Senator Peter Oberacker\nNew York State Senate\nLegislative Office Building, Room 319\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Lea Webb",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:Lea_Webb_2023.jpg",
                "518-455-2711",
                "Lea Webb is a Democratic State Senator for New York's 52nd District including Cortland, Tompkins, and part of Broome Counties since 2023. A lifelong Southern Tier resident, she succeeded Republican Fred Akshar.",
                "Senator Lea Webb\nNew York State Senate\nLegislative Office Building, Room 407\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Joseph A. Griffo",
                "Republican",
                "https://en.wikipedia.org/wiki/File:Joseph_Griffo_2019.jpg",
                "518-455-2071",
                "Joseph A. Griffo (born January 16, 1956) is a Republican State Senator for New York's 53rd District since 2007. Former mayor of Rome and Oneida County Executive, he serves as deputy minority leader.",
                "Senator Joseph A. Griffo\nNew York State Senate\nLegislative Office Building, Room 803\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Pamela A. Helming",
                "Republican",
                "https://en.wikipedia.org/wiki/File:Pam_Helming_2018.jpg",
                "518-455-2322",
                "Pamela A. Helming (born February 18, 1962) is a Republican State Senator for New York's 54th District since 2017. Former Canandaigua town supervisor, she succeeded Sen. Michael Nozzolio.",
                "Senator Pamela A. Helming\nNew York State Senate\nLegislative Office Building, Room 505\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Samra G. Brouk",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:Samra_Brouk_2021.jpg",
                "518-455-2505",
                "Samra G. Brouk (born May 20, 1986) is a Democratic State Senator for New York's 55th District since 2021. Daughter of Ethiopian immigrants and former nonprofit worker, she chairs the Mental Health Committee.",
                "Senator Samra G. Brouk\nNew York State Senate\nLegislative Office Building, Room 416\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Jeremy A. Cooney",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:(04-05-22)_NYS_Senator_Jeremy_Cooney_(cropped).jpg",
                "518-455-2511",
                "Jeremy Cooney (born August 1, 1981) is a Democratic State Senator for New York's 56th District since 2021. First Asian-American state legislator from upstate NY, he chairs the Transportation Committee.",
                "Senator Jeremy A. Cooney\nNew York State Senate\nLegislative Office Building, Room 420\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "George M. Borrello",
                "Republican",
                "https://en.wikipedia.org/wiki/File:George_Borrello_2020.jpg",
                "518-455-2231",
                "George M. Borrello (born May 27, 1967) is a Republican State Senator for New York's 57th District since 2019. Former Chautauqua County Executive, he won a special election to succeed Catharine Young.",
                "Senator George M. Borrello\nNew York State Senate\nLegislative Office Building, Room 308\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Thomas Fellers O'Mara",
                "Republican",
                "https://en.wikipedia.org/wiki/File:Tom_O%27Mara_2013.jpg",
                "518-455-2106",
                "Thomas F. O'Mara (born May 31, 1963) is a Republican State Senator for New York's 58th District since 2011. A lawyer from Big Flats, he previously served in the Assembly.",
                "Senator Thomas Fellers O'Mara\nNew York State Senate\nLegislative Office Building, Room 403\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Kristen Gonzalez",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:Kristen_Gonzalez_2023.jpg",
                "518-455-2651",
                "Kristen Serene Gonzalez is a Democratic State Senator for New York's 59th District since 2023 covering parts of Queens, Manhattan, and Brooklyn. Former legislative staffer for NYC Council and Sen. Schumer.",
                "Senator Kristen Gonzalez\nNew York State Senate\nLegislative Office Building, Room 322\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Patrick M. Gallivan",
                "Republican",
                "https://en.wikipedia.org/wiki/File:Patrick_Gallivan_2015.jpg",
                "518-455-3425",
                "Patrick M. Gallivan (born November 18, 1960) is a Republican State Senator for New York's 60th District since 2011. Former Erie County Sheriff, he represents parts of Erie, Wyoming, Livingston, and Monroe Counties.",
                "Senator Patrick M. Gallivan\nNew York State Senate\nLegislative Office Building, Room 548\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Sean M. Ryan",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:Sean_Ryan_(State_Senator).jpg",
                "518-455-3144",
                "Sean M. Ryan (born March 10, 1965) is a Democratic State Senator for New York's 61st District since 2023. Former Assembly member and attorney from Buffalo, he has focused on housing policy and consumer protection.",
                "Senator Sean M. Ryan\nNew York State Senate\nLegislative Office Building, Room 512\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Edward F. Rath III",
                "Republican",
                "https://en.wikipedia.org/wiki/File:Ed_Rath_2021.jpg",
                "518-455-3161",
                "Edward F. Rath III (born July 30, 1972) is a Republican State Senator for New York's 62nd District since 2021. Former Erie County Legislator, he is part of a well-known Western New York political family.",
                "Senator Edward F. Rath III\nNew York State Senate\nLegislative Office Building, Room 615\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Robert G. Ortt",
                "Republican",
                "https://en.wikipedia.org/wiki/File:Rob_Ortt_2015.jpg",
                "518-455-2024",
                "Robert G. Ortt (born April 23, 1979) is the Republican Minority Leader of the New York State Senate and represents the 63rd District. Former mayor of North Tonawanda and U.S. Army veteran who deployed to Afghanistan.",
                "Senator Robert G. Ortt\nNew York State Senate\nLegislative Office Building, Room 315\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Andrea Stewart-Cousins",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:Andrea_Stewart-Cousins_(2022).jpg",
                "518-455-2585",
                "Andrea Stewart-Cousins (born September 2, 1950) is a Democratic State Senator representing New York's 35th District and has served as Senate Majority Leader since 2019. Former Yonkers City Council member, she is the first woman and first Black woman to lead the chamber.",
                "Senator Andrea Stewart-Cousins\nNew York State Senate\nLegislative Office Building, Room 907\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Shelley B. Mayer",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:Shelley_Mayer_2022.jpg",
                "518-455-2031",
                "Shelley B. Mayer (born March 6, 1952) is a Democratic State Senator for New York's 37th District since 2018. Former Assembly member and education advocate from Yonkers.",
                "Senator Shelley B. Mayer\nNew York State Senate\nLegislative Office Building, Room 310\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Jamaal T. Bailey",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:Jamaal_Bailey_2021.jpg",
                "518-455-2061",
                "Jamaal T. Bailey (born July 10, 1982) is a Democratic State Senator for New York's 36th District and chair of the Senate Codes Committee. He is also the Bronx Democratic Party Leader.",
                "Senator Jamaal T. Bailey\nNew York State Senate\nLegislative Office Building, Room 718\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Nathalia Fernandez",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:Nathalia_Fernandez_2023.jpg",
                "518-455-2700",
                "Nathalia Fernandez is a Democratic State Senator representing New York's 34th District since 2023. Former Assembly Member and policy advisor in the Governor's Office.",
                "Senator Nathalia Fernandez\nNew York State Senate\nLegislative Office Building, Room 315\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Luis R. Sepúlveda",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:Luis_Sepulveda_2019.jpg",
                "518-455-2511",
                "Luis R. Sepúlveda (born January 21, 1964) is a Democratic State Senator for New York's 32nd District. Former Assembly member and attorney focusing on immigration and criminal justice reforms.",
                "Senator Luis R. Sepúlveda\nNew York State Senate\nLegislative Office Building, Room 409\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Robert Jackson",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:Robert_Jackson_(State_Senator).jpg",
                "518-455-2041",
                "Robert Jackson (born December 18, 1950) is a Democratic State Senator for New York's 31st District. Former NYC Council member known for leading the Campaign for Fiscal Equity lawsuit.",
                "Senator Robert Jackson\nNew York State Senate\nLegislative Office Building, Room 713\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Cordell Cleare",
                "Democratic",
                "https://en.wikipedia.org/wiki/File:Cordell_Cleare_2023.jpg",
                "518-455-2441",
                "Cordell Cleare (born April 27, 1965) is a Democratic State Senator for New York's 30th District. Former chief of staff to Bill Perkins and longtime tenant and civil rights advocate.",
                "Senator Cordell Cleare\nNew York State Senate\nLegislative Office Building, Room 503\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Brian A. Benjamin",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/3/3a/Brian_A._Benjamin_%28official_portrait%2C_2021%29.jpg",
                "Phone Unavailable",
                "Brian A. Benjamin (born December 9, 1976) is an American politician who served as Lieutenant Governor of New York in 2021–2022 and previously represented Harlem in the New York State Senate.",
                "New York State Senate\nDistrict Office\nHarlem, NY"
        ));

        list.add(new Politician(
                "Gustavo Rivera",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/2/2d/Gustavo_Rivera_2020.jpg",
                "518-455-3395",
                "Gustavo Rivera (born November 19, 1975) is a Democratic State Senator for New York’s 33rd District. He chairs the Senate Health Committee and is known for sponsoring the New York Health Act.",
                "Senator Gustavo Rivera\nNew York State Senate\nLegislative Office Building, Room 502\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Jabari Brisport",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/3/33/Jabari_Brisport_2021.jpg",
                "518-455-2437",
                "Jabari Brisport (born August 9, 1987) is a Democratic Socialist State Senator representing New York’s 25th District. He is the first openly gay person of color elected to statewide office in New York.",
                "Senator Jabari Brisport\nNew York State Senate\nLegislative Office Building, Room 915\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Zellnor Myrie",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/8/83/Zellnor_Myrie_2023.jpg",
                "518-455-2410",
                "Zellnor Myrie (born February 21, 1986) is a Democratic State Senator for New York’s 20th District. He is a leading advocate on gun control, voting rights, and tenant protections.",
                "Senator Zellnor Myrie\nNew York State Senate\nLegislative Office Building, Room 512\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Kevin S. Parker",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/9/99/Kevin_Parker_2020.jpg",
                "518-455-2580",
                "Kevin Parker (born March 6, 1967) is a Democratic State Senator representing the 21st District in Brooklyn. He has served since 2003 and focuses heavily on energy and environmental issues.",
                "Senator Kevin S. Parker\nNew York State Senate\nLegislative Office Building, Room 502\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Andrew Gounardes",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/6/6c/Andrew_Gounardes_2023.jpg",
                "518-455-3260",
                "Andrew Gounardes (born July 22, 1985) is a Democratic State Senator for New York’s 26th District. He represents parts of Brooklyn and is a strong advocate for pedestrian safety and workers’ rights.",
                "Senator Andrew Gounardes\nNew York State Senate\nLegislative Office Building, Room 803\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Iwen Chu",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/7/7e/Iwen_Chu_2023.jpg",
                "518-455-2735",
                "Iwen Chu is the first Asian American woman elected to the New York State Senate. She represents District 17 (Brooklyn) and formerly worked as a community liaison.",
                "Senator Iwen Chu\nNew York State Senate\nLegislative Office Building, Room 506\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Simcha Felder",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/4/41/Simcha_Felder_2018.jpg",
                "518-455-2754",
                "Simcha Felder (born December 29, 1958) is a New York State Senator representing District 17/22 (varied through redistricting). He is known for caucusing with Republicans and Democrats at different times.",
                "Senator Simcha Felder\nNew York State Senate\nLegislative Office Building, Room 806\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Brian Kavanagh",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/f/f7/Brian_Kavanagh_2022.jpg",
                "518-455-2625",
                "Brian Kavanagh (born January 18, 1967) is a Democratic State Senator representing New York’s 27th District. Former Assembly Member known for gun control legislation and tenant protections.",
                "Senator Brian Kavanagh\nNew York State Senate\nLegislative Office Building, Room 502\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Liz Krueger",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/7/77/Liz_Krueger_2019.jpg",
                "518-455-2297",
                "Liz Krueger (born November 15, 1957) is a Democratic State Senator representing District 28 in Manhattan. She chairs the Senate Finance Committee.",
                "Senator Liz Krueger\nNew York State Senate\nLegislative Office Building, Room 416\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "José M. Serrano",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/0/03/Jos%C3%A9_Serrano_2019.jpg",
                "518-455-2795",
                "José M. Serrano (born June 19, 1972) represents New York’s 29th State Senate District. He focuses on parks, arts, and cultural preservation.",
                "Senator José M. Serrano\nNew York State Senate\nLegislative Office Building, Room 506\nAlbany, NY 12247"
        ));

        list.add(new Politician(
                "Terry J. Gipson",
                "Democratic",
                "Phone Unavailable",
                "Phone Unavailable",
                "Terry J. Gipson served as a New York State Senator representing the 41st District (Hudson Valley).",
                "New York State Senate\nAlbany, NY"
        ));

        list.add(new Politician(
                "David Carlucci",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/3/38/David_Carlucci_2018.jpg",
                "Phone Unavailable",
                "David Carlucci represented New York’s 38th Senate District from 2011 to 2020. Member of the Independent Democratic Conference.",
                "New York State Senate\nAlbany, NY"
        ));

        list.add(new Politician(
                "John Brooks",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/2/26/John_Brooks_2017.jpg",
                "518-455-2765",
                "John Brooks represented District 8 in the New York State Senate. Former firefighter and insurance professional.",
                "New York State Senate\nAlbany, NY"
        ));

        list.add(new Politician(
                "Diane Savino",
                "Democratic",
                "https://upload.wikimedia.org/wikipedia/commons/a/a9/Diane_Savino_2015.jpg",
                "(718) 727-9406",
                "Diane Savino served as a New York State Senator representing District 23. Former member of the Independent Democratic Conference.",
                "New York State Senate\nAlbany, NY"
        ));


        return list;
    }

    public static List<User> getUsers()
    {
        List<User> list = new ArrayList<>();
        list.add(new User("admin", "admin@voteinformed.com", "voteinformedadmin", "NYC", "[admin preferences]", true ));
        list.add(new User("Andrew", "aperro01@nyit.edu", "perrone", "NYC", "[admin preferences]", true ));
        list.add(new User("[name]", "[email]", "[password]", "[location]", "[preferences]"));
        return list;
    }


}
