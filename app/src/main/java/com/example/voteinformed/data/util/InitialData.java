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

    public static List<Article> getArticles()
    {
        List<Article> list = new ArrayList<>();
        list.add(new Article("title","[url]"));
        return list;
    }

    public static List<Election> getElections()
    {
        List<Election> list = new ArrayList<>();
        list.add(new Election("[election name]", new Date(), "[location]", "[description]"));
        return list;
    }

    public static List<Issue> getIssues()
    {
        List<Issue> list = new ArrayList<>();
        list.add(new Issue("[title]", "[description]", "[type]", "[location]"));
        return list;
    }

    private static final byte[] default_image = new byte[1];

    public static List<Politician> getPoliticians()
    {
        List<Politician> list = new ArrayList<>();

        list.add(new Politician(
                "Donald Trump",
                "Republican",
                default_image,
                "[Contact information varies; typically through campaign or official organization websites]",
                "Donald John Trump (born 1946) is an American politician, media personality, and businessman who served as the 45th President of the United States from 2017 to 2021. Prior to entering politics, he was a real estate developer and television personality.",
                "The United States (Former President)"
        ));

        list.add(new Politician(
                "Kathy Hochul",
                "Democratic",
                default_image,
                "518-474-8390",
                "Hochul was born in Buffalo, New York. She received a B.A. from Syracuse University in 1980 and a J.D. from Catholic University in 1983. In addition to working as an attorney in private practice, she was an aide to Rep. John LaFalce (D) from 1984 to 1986 and to Sen. Daniel Patrick Moynihan (D) from 1986 to 1988. She served as Deputy Clerk of Erie County in New York from 2003 to 2007 and as Clerk of Erie County from 2007 to 2011.",
                "The Honorable Kathy Hochul\nGovernor of New York State\nNYS State Capitol Building\nAlbany, NY 12224"
        ));

        list.add(new Politician(
                "Alexandria Ocasio-Cortez",
                "Democratic",
                default_image,
                "718-662-5970",
                "Alexandria Ocasio-Cortez graduated from Boston University College of Arts & Sciences in 2011. She was a volunteer organizer for Sanders' presidential campaign and worked in former Sen. Ted Kennedy's (D-Mass.) foreign affairs and immigration office. Ocasio-Cortez founded Brook Avenue Press, a children's book publisher.[",
                "Washington DC Office\n250 Cannon HOB\nWashington, DC  20515\nAstoria Office\n30-83 31st Street\nQueens, NY  11102\nBy Appointment Only\n\nHunts Point Office\n1231 Lafayette Ave\nSuite L-610\nBronx, NY  10474\nOpen Monday through Thursday\n9 am to 5 pm"
        ));

        list.add(new Politician(
                "Andrew Cuomo",
                "Democratic",
                default_image,
                "518-474-8390",
                "Cuomo was born on December 6, 1957, in Queens, New York. The son of former Governor Mario Cuomo (D), he graduated from Archbishop Molloy High School, Fordham University, and Albany Law School. Cuomo was his father's campaign manager. He headed his father's transition committee and served as an advisor during the senior Cuomo's governorship.\nCuomo worked as a New York assistant district attorney and for the law firm of Blutrich, Falcone & Miller between 1984 and 1986, when he founded the Housing Enterprise for the Less Privileged (HELP). Before becoming governor, Cuomo worked at the federal level, serving as assistant secretary of the U.S. Department of Housing and Urban Development (HUD) from 1993 to 1997 and as secretary of HUD from 1997 to 2001 under President Bill Clint",
                "The Honorable Andrew M. Cuomo Governor of New York State NYS\nState Capitol Building Albany, NY 12224 (Note: This is a historical address as he is a former governor.)"
        ));

        list.add(new Politician(
                "Curtis Sliwa",
                "Republican",
                default_image,
                "Media Contact: Maria@SliwaForNYC.com\nCampaign Contact: info@SliwaForNYC.com",
                "Eric Adams (D), Curtis Sliwa (R), and eight other candidates ran in the general election for mayor of New York City on November 2, 2021.[1] Incumbent Mayor Bill de Blasio (D) did not run for re-election due to term limits.\nThe primary election on June 22, 2021, featured the first use of ranked-choice voting (RCV) for a mayoral primary in the city's history. Click here to read more about how ranked-choice voting works.\nThe top issues in the Democratic primary were crime, policing, affordable housing, jobs, and healthcare.[2] Click here to learn more about the Democratic primary.\nDe Blasio was first elected in 2013 and won re-election in 2017 with 66% of the vote. Including de Blasio, four of the previous six mayors were Democrats.",
                "N/A (Note: Curtis Sliwa ran for Mayor of NYC in 2021.)"
        ));

        list.add(new Politician(
                "Zohran Mamdani",
                "Democratic",
                default_image,
                "District Office: 718-545-3889 \n Albany Office: 518-455-5014",
                "Zohran Kwame Mamdani was born in Kampala, Uganda.[1][2] Mamdani graduated from Bronx High School of Science. He earned a bachelor's degree in Africana studies from Bowdoin College. Mamdani's career experience includes working as a foreclosure prevention housing counselor.",
                "District Office\n24-08 32nd Street\nSuite 1002A\nAstoria, NY 11102" + "\n\nAlbany Office\nLOB 456\nAlbany, NY 12248"
        ));

        list.add(new Politician(
                "Eric Adams",
                "Democratic",
                default_image,
                "Email: mayoreric@cityhall.nyc.gov",
                "Eric Adams graduated from Bayside High School. Adams earned a master's degree in public administration from Marist College and degrees from New York City Technical College and the John Jay College of Criminal Justice. His career experience includes working as a captain with the New York Police Department. Adams founded 100 Blacks in Law Enforcement Who Care",
                "Mayor Eric Adams\nCity Hall\nNew York, NY 10007"
        ));

        list.add(new Politician(
                "Nick LaLota",
                "Republican",
                default_image,
                "Washington DC Office: 202-225-3826 \n Hauppauge District Office: 631-289-1097",
                "LaLota was born on June 23, 1978, and is from Bay Shore, New York. He graduated from St. Anthony's High School and, in 2000, from the United States Naval Academy. He served in the U.S. Navy for eight years, serving three overseas deployments. He earned a Master of Business Administration and a Juris Doctor from Hofstra University. LaLota served as chief of staff to Suffolk County presiding officer Kevin McCaffrey. He also served on the Suffolk Board of Elections as well as a trustee for the village of Amityville, New York.",
                "Washington DC Office \n 122 Cannon House Office Building\nWashington, DC  20515\nPhone: (202) 225-3826\nHauppauge District Office\n515 Hauppauge Road\nSuite 3B\nHauppauge, NY  11788"
        ));

        list.add(new Politician(
                "Andrew Garbarino",
                "Republican",
                default_image,
                "Washington Dc Office: 202-255-7896 \n Patchogue District Office: 631-541-4225",
                "Andrew Garbarino is a lifelong Long Islander who has served as the U.S. Representative for New York's 2nd Congressional District since 2021 and is currently the Chairman of the House Committee on Homeland Security. Before entering Congress, he was a member of the New York State Assembly for four terms and worked as an attorney at his family's law firm. He earned a bachelor's degree from George Washington University and a law degree from Hofstra University. ",
                "Washington DC Office\n2344 Rayburn House Office Building\nWashington, DC  20515"+"\n\nPatchogue District Office\n31 Oak Street Suite 20\nPatchogue, NY  11772"
        ));

        list.add(new Politician(
                "Tom Suozzi",
                "Democratic", default_image,
                "Official Website Contact Page: https://suozzi.house.gov/contact/ \n DC Office Phone: (202) 225-3335",
                "Tom Suozzi (born 1962) is an American attorney and politician who has served as the U.S. Representative for New York's 3rd congressional district since 2024. He previously held the seat from 2017 to 2023. Prior to Congress, Suozzi served as the Nassau County Executive and as the Mayor of Glen Cove.",
                "3rd district (since 2024) \n D.C. Address: 1726 Longworth HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Laura Gillen",
                "Democratic", default_image,
                "Official Website Contact Page: [Will be available after inauguration] \n DC Office Phone: [Will be available after inauguration]",
                "Laura Gillen is an American attorney and politician, set to serve as the U.S. Representative for New York's 4th congressional district starting in 2025. She previously served as the Hempstead Town Supervisor from 2018 to 2019. Gillen is known for her work in local government and her focus on community issues.",
                "4th district (since 2025) \n D.C. Address: [Will be available after inauguration]"));

        list.add(new Politician(
                "Gregory Meeks",
                "Democratic", default_image,
                "Official Website Contact Page: https://meeks.house.gov/contact/ \n DC Office Phone: (202) 225-3461",
                "Gregory W. Meeks (born 1953) has served as the U.S. Representative for New York's 5th congressional district since 1998. He is a senior member of the House Foreign Affairs Committee and a former Chair of the Congressional Black Caucus.",
                "5th district (since 1998) \n D.C. Address: 2225 Rayburn HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Grace Meng",
                "Democratic", default_image,
                "Official Website Contact Page: https://meng.house.gov/contact/ \n DC Office Phone: (202) 225-2601",
                "Grace Meng (born 1975) is the U.S. Representative for New York's 6th congressional district, serving since 2013. She is the first Asian American member of Congress from New York. Prior to her service in Congress, she was a member of the New York State Assembly.",
                "6th district (since 2013) \n D.C. Address: 2420 Rayburn HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Nydia Velázquez",
                "Democratic", default_image,
                "Official Website Contact Page: https://velazquez.house.gov/contact/ \n DC Office Phone: (202) 225-2361",
                "Nydia M. Velázquez (born 1953) has represented New York's 7th congressional district since 1993. She is the first Puerto Rican woman to be elected to the U.S. Congress. Velázquez serves as the Ranking Member of the House Small Business Committee.",
                "7th district (since 1993) \n D.C. Address: 2354 Rayburn HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Hakeem Jeffries",
                "Democratic", default_image,
                "Official Website Contact Page: https://jeffries.house.gov/contact/ \n DC Office Phone: (202) 225-5936",
                "Hakeem S. Jeffries (born 1970) is the U.S. Representative for New York's 8th congressional district, serving since 2013. He currently serves as the House Minority Leader, making him the highest-ranking African American in Congress.",
                "8th district (since 2013) \n D.C. Address: 2433 Rayburn HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Yvette Clarke",
                "Democratic", default_image,
                "Official Website Contact Page: https://clarke.house.gov/contact/ \n DC Office Phone: (202) 225-6231",
                "Yvette D. Clarke (born 1964) has served as the U.S. Representative for New York's 9th congressional district since 2007. She is known for her work on technology policy and her focus on the Caribbean-American community. She previously served on the New York City Council.",
                "9th district (since 2007) \n D.C. Address: 2058 Rayburn HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Dan Goldman",
                "Democratic", default_image,
                "Official Website Contact Page: https://goldman.house.gov/contact/ \n DC Office Phone: (202) 225-7944",
                "Daniel S. Goldman (born 1976) has been the U.S. Representative for New York's 10th congressional district since 2023. He previously served as lead counsel for the Democrats in the first impeachment inquiry against President Donald Trump.",
                "10th district (since 2023) \n D.C. Address: 1610 Longworth HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Nicole Malliotakis",
                "Republican", default_image,
                "Official Website Contact Page: https://malliotakis.house.gov/contact/ \n DC Office Phone: (202) 225-3371",
                "Nicole Malliotakis (born 1980) has served as the U.S. Representative for New York's 11th congressional district since 2021. She is the only Republican representing a district that includes a portion of New York City (Staten Island and parts of Brooklyn). She previously served in the New York State Assembly.",
                "11th district (since 2021) \n D.C. Address: 104 Cannon HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Jerry Nadler",
                "Democratic", default_image,
                "Official Website Contact Page: https://nadler.house.gov/contact/ \n DC Office Phone: (202) 225-5635",
                "Jerrold L. Nadler (born 1947) has represented New York's 12th congressional district (and previously the 8th and 10th districts) since 1992. He is a prominent figure on the House Judiciary Committee and a long-serving progressive voice.",
                "12th district (since 1992) \n D.C. Address: 2136 Rayburn HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Adriano Espaillat",
                "Democratic", default_image,
                "Official Website Contact Page: https://espaillat.house.gov/contact/ \n DC Office Phone: (202) 225-4365",
                "Adriano Espaillat (born 1954) has served as the U.S. Representative for New York's 13th congressional district since 2017. He is the first Dominican American to serve in Congress. Prior to Congress, he served in the New York State Senate and Assembly.",
                "13th district (since 2017) \n D.C. Address: 147 Cannon HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Alexandria Ocasio-Cortez",
                "Democratic", default_image,
                "Official Website Contact Page: https://ocasio-cortez.house.gov/contact/ \n DC Office Phone: (202) 225-3965",
                "Alexandria Ocasio-Cortez (born 1989), often referred to as AOC, has been the U.S. Representative for New York's 14th congressional district since 2019. She is a highly visible progressive figure and member of the Democratic Socialists of America.",
                "14th district (since 2019) \n D.C. Address: 2452 Rayburn HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Ritchie Torres",
                "Democratic", default_image,
                "Official Website Contact Page: https://torres.house.gov/contact/ \n DC Office Phone: (202) 225-8361",
                "Ritchie J. Torres (born 1988) has served as the U.S. Representative for New York's 15th congressional district since 2021. He is the first openly gay Afro-Latino member of Congress. He previously served on the New York City Council.",
                "15th district (since 2021) \n D.C. Address: 228 Cannon HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "George Latimer",
                "Democratic", default_image,
                "Official Website Contact Page: [Will be available after inauguration] \n DC Office Phone: [Will be available after inauguration]",
                "George S. Latimer is an American politician, set to serve as the U.S. Representative for New York's 16th congressional district starting in 2025. He previously served as the Westchester County Executive, and has held seats in both the New York State Senate and Assembly.",
                "16th district (since 2025) \n D.C. Address: [Will be available after inauguration]"));

        list.add(new Politician(
                "Mike Lawler",
                "Republican", default_image,
                "Official Website Contact Page: https://lawler.house.gov/contact/ \n DC Office Phone: (202) 225-6506",
                "Michael V. Lawler (born 1986) has served as the U.S. Representative for New York's 17th congressional district since 2023. Prior to Congress, he served as a member of the New York State Assembly. Lawler is known for his background in business and public service.",
                "17th district (since 2023) \n D.C. Address: 432 Cannon HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Pat Ryan",
                "Democratic", default_image,
                "Official Website Contact Page: https://ryan.house.gov/contact/ \n DC Office Phone: (202) 225-5441",
                "Patrick K. Ryan (born 1982) is the U.S. Representative for New York's 18th congressional district, serving since 2022. He is a veteran of the U.S. Army, having served two tours in Iraq. Prior to Congress, he served as the County Executive of Ulster County.",
                "18th district (since 2022) \n D.C. Address: 104 Cannon HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Josh Riley",
                "Democratic", default_image,
                "Official Website Contact Page: [Will be available after inauguration] \n DC Office Phone: [Will be available after inauguration]",
                "Josh Riley is an American attorney and politician, set to serve as the U.S. Representative for New York's 19th congressional district starting in 2025. He has worked as a public defender and has a focus on bringing economic opportunity to Upstate New York.",
                "19th district (since 2025) \n D.C. Address: [Will be available after inauguration]"));

        list.add(new Politician(
                "Paul Tonko",
                "Democratic", default_image,
                "Official Website Contact Page: https://tonko.house.gov/contact/ \n DC Office Phone: (202) 225-5076",
                "Paul David Tonko (born 1949) has served as the U.S. Representative for New York's 20th congressional district since 2009. He has a long history of public service, including serving in the New York State Assembly. He focuses on energy and environmental policy.",
                "20th district (since 2009) \n D.C. Address: 2465 Rayburn HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Elise Stefanik",
                "Republican", default_image,
                "Official Website Contact Page: https://stefanik.house.gov/contact/ \n DC Office Phone: (202) 225-4611",
                "Elise M. Stefanik (born 1984) is the U.S. Representative for New York's 21st congressional district, serving since 2015. She is the current Chair of the House Republican Conference, the third-highest-ranking position in House Republican leadership.",
                "21st district (since 2015) \n D.C. Address: 2167 Rayburn HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "John Mannion",
                "Democratic", default_image,
                "Official Website Contact Page: [Will be available after inauguration] \n DC Office Phone: [Will be available after inauguration]",
                "John W. Mannion is an American politician, set to serve as the U.S. Representative for New York's 22nd congressional district starting in 2025. He previously served in the New York State Senate and has a background as a teacher.",
                "22nd district (since 2025) \n D.C. Address: [Will be available after inauguration]"));

        list.add(new Politician(
                "Nick Langworthy",
                "Republican", default_image,
                "Official Website Contact Page: https://langworthy.house.gov/contact/ \n DC Office Phone: (202) 225-3161",
                "Nick A. Langworthy (born 1981) has been the U.S. Representative for New York's 23rd congressional district since 2023. Prior to Congress, he served as the Chairman of the New York State Republican Committee.",
                "23rd district (since 2023) \n D.C. Address: 1531 Longworth HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Claudia Tenney",
                "Republican", default_image,
                "Official Website Contact Page: https://tenney.house.gov/contact/ \n DC Office Phone: (202) 225-4376",
                "Claudia Tenney (born 1961) has served as the U.S. Representative for New York's 24th congressional district (and previously the 22nd district) since 2021. She is an attorney and businesswoman, known for her conservative stance.",
                "24th district (since 2021) \n D.C. Address: 2408 Rayburn HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Joseph Morelle",
                "Democratic", default_image,
                "Official Website Contact Page: https://morelle.house.gov/contact/ \n DC Office Phone: (202) 225-3615",
                "Joseph D. Morelle (born 1957) is the U.S. Representative for New York's 25th congressional district, serving since 2018. He previously served in the New York State Assembly, including a term as the Majority Leader.",
                "25th district (since 2018) \n D.C. Address: 2369 Rayburn HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Tim Kennedy",
                "Democratic", default_image,
                "Official Website Contact Page: https://kennedy.house.gov/contact/ \n DC Office Phone: (202) 225-3306",
                "Timothy M. Kennedy (born 1976) has served as the U.S. Representative for New York's 26th congressional district since 2024. He previously served in the New York State Senate. Kennedy has a background in healthcare administration and is focused on local infrastructure.",
                "26th district (since 2024) \n D.C. Address: 1726 Longworth HOB, Washington, D.C. 20515"));

        list.add(new Politician(
                "Chuck Schumer",
                "Democratic",
                default_image,
                "DC Office Phone: (202) 224-6542 \n Official Website Contact: https://www.schumer.senate.gov/contact/email-chuck",
                "Charles Ellis Schumer (born 1950) is the senior U.S. Senator from New York, a seat he has held since 1999. He currently serves as the Senate Majority Leader. He previously served in the U.S. House of Representatives and the New York State Assembly.",
                "U.S. Senator for New York \n D.C. Address: 322 Hart Senate Office Building, Washington, DC 20510"
        ));

        list.add(new Politician(
                "Kirsten Gillibrand",
                "Democratic",
                default_image,
                "DC Office Phone: (202) 224-4451 \n Official Website Contact: https://www.gillibrand.senate.gov/contact",
                "Kirsten Elizabeth Gillibrand (born 1966) is the junior U.S. Senator from New York, serving since 2009. She previously represented New York's 20th congressional district in the House. She is known for her advocacy on military justice and sexual assault reforms.",
                "U.S. Senator for New York \n D.C. Address: 478 Russell Senate Office Building, Washington, DC 20510"
        ));

        list.add(new Politician(
                "Carl Heastie",
                "Democratic",
                default_image,
                "Albany Office Phone: (518) 455-3791 \n District Office: (718) 654-6539",
                "Carl E. Heastie (born 1967) has served as the Speaker of the New York State Assembly since 2015. He represents the 83rd Assembly District in the Bronx. Prior to his service, he worked as a budget analyst for the New York City Comptroller's office.",
                "Speaker of the New York State Assembly \n Albany Office: LOB 932, Albany, NY 12248"
        ));

        list.add(new Politician(
                "Andrea Stewart-Cousins",
                "Democratic",
                default_image,
                "Albany Office Phone: (518) 455-2585 \n District Office: (914) 423-4031",
                "Andrea Stewart-Cousins (born 1950) is the Temporary President and Majority Leader of the New York State Senate, serving since 2019. She is the first female legislative leader in New York State history. She represents the 35th Senate District (Westchester County).",
                "Majority Leader of the New York State Senate \n Albany Office: LOB 907, Albany, NY 12248"
        ));

        list.add(new Politician(
                "Letitia James",
                "Democratic",
                default_image,
                "General Office Phone: (212) 416-8000 \n TTY: (800) 788-9898",
                "Letitia 'Tish' James (born 1958) is the Attorney General of New York State, serving since 2019. She is the first African American and first woman to be elected to this office. She previously served as the New York City Public Advocate.",
                "New York State Attorney General \n Main Office: 28 Liberty Street, New York, NY 10005"
        ));

        list.add(new Politician(
                "Thomas DiNapoli",
                "Democratic",
                default_image,
                "Main Office Phone: (518) 474-4015",
                "Thomas P. DiNapoli (born 1954) is the Comptroller of New York State, serving since 2007. He is the sole trustee of the third-largest public pension fund in the United States. He previously served in the New York State Assembly for two decades.",
                "New York State Comptroller \n Main Office: 110 State Street, Albany, NY 12236"
        ));

        list.add(new Politician(
                "Adrienne Adams",
                "Democratic",
                default_image,
                "City Hall Office Phone: (212) 788-7210 \n District Office: (718) 206-2068",
                "Adrienne E. Adams (born 1960) has served as the Speaker of the New York City Council since 2022. She is the first Black person to hold the position. She represents District 28 in Queens.",
                "Speaker of the New York City Council \n City Hall: 250 Broadway, Suite 1734, New York, NY 10007"
        ));

        list.add(new Politician(
                "Jerry Nadler",
                "Democratic",
                default_image,
                "DC Office Phone: (202) 225-5635",
                "Jerrold L. Nadler (born 1947) has represented New York's 12th congressional district since 2013 (and various districts since 1992). He is a long-time member of Congress known for his work on the House Judiciary Committee.",
                "U.S. Representative (12th District) \n D.C. Address: 2136 Rayburn HOB, Washington, D.C. 20515"
        ));

        list.add(new Politician(
                "Lee Zeldin",
                "Republican",
                default_image,
                "Campaign/General Inquiries: [Varies by activity] \n Former Congressional Office: (202) 225-3826 (Historical)",
                "Lee M. Zeldin (born 1980) is an American attorney, politician, and officer in the U.S. Army Reserve. He served as the U.S. Representative for New York's 1st congressional district from 2015 to 2023. He was the Republican nominee for Governor of New York in 2022.",
                "Long Island, New York (Former U.S. Congressman)"
        ));

        list.add(new Politician(
                "Ron Kim",
                "Democratic",
                default_image,
                "District Office Phone: (718) 939-0740 \n Albany Office: (518) 455-5411",
                "Ron Kim (born 1979) is a member of the New York State Assembly, representing the 40th District (Queens). He is the first Korean-American ever elected to the New York State Legislature. He focuses on elder care and economic justice.",
                "New York State Assembly (40th District) \n District Office: 136-20 Roosevelt Ave, Suite 305, Flushing, NY 11354"
        ));

        list.add(new Politician(
                "Jessica Ramos",
                "Democratic",
                default_image,
                "District Office Phone: (718) 205-3881 \n Albany Office: (518) 455-3341",
                "Jessica Ramos (born 1982) is a member of the New York State Senate, representing the 13th District (Queens). She is the Chair of the Senate Committee on Labor and is known for her work on worker's rights and immigrant protections.",
                "New York State Senate (13th District) \n District Office: 76-02 37th Avenue, Suite 205, Jackson Heights, NY 11372"
        ));

        list.add(new Politician(
                "Crystal Peoples-Stokes",
                "Democratic",
                default_image,
                "Albany Office Phone: (518) 455-5021 \n District Office: (716) 897-9035",
                "Crystal Peoples-Stokes (born 1952) has served as the Majority Leader of the New York State Assembly since 2018. She represents the 141st Assembly District (Buffalo). She is a long-time advocate for community development and social justice.",
                "Majority Leader of the New York State Assembly \n Albany Office: LOB 925, Albany, NY 12248"
        ));

        list.add(new Politician(
                "Andrew Gounardes",
                "Democratic",
                default_image,
                "District Office Phone: (718) 517-3199 \n Albany Office: (518) 455-2330",
                "Andrew Gounardes (born 1985) is a member of the New York State Senate, representing the 26th District (Brooklyn). He is known for his work on transportation, education funding, and combating climate change.",
                "New York State Senate (26th District) \n District Office: 8018 5th Ave, Brooklyn, NY 11209"
        ));

        list.add(new Politician(
                "Inez Dickens",
                "Democratic",
                default_image,
                "District Office Phone: (212) 234-1430 \n Albany Office: (518) 455-5623",
                "Inez E. Dickens (born 1949) is a member of the New York State Assembly, representing the 70th District (Harlem). She has a long history in Harlem politics, having previously served on the New York City Council.",
                "New York State Assembly (70th District) \n District Office: 163 West 125th Street, 8th Floor, New York, NY 10027"
        ));

        list.add(new Politician(
                "Michael Gianaris",
                "Democratic",
                default_image,
                "District Office Phone: (718) 545-9717 \n Albany Office: (518) 455-3276",
                "Michael N. Gianaris (born 1970) is the Deputy Majority Leader of the New York State Senate. He represents the 12th District (Queens). He is known for his progressive legislation and stance on corporate accountability.",
                "Deputy Majority Leader of the New York State Senate \n District Office: 31-19 Newtown Ave, Suite 402, Astoria, NY 11102"
        ));

        list.add(new Politician(
                "Simcha Eichenstein",
                "Democratic",
                default_image,
                "District Office Phone: (718) 853-9119 \n Albany Office: (518) 455-5803",
                "Simcha Eichenstein (born 1983) is a member of the New York State Assembly, representing the 48th District (Brooklyn). He is the first Hasidic Jewish elected official to serve in the New York State Legislature. His focus is on the needs of the Orthodox Jewish community.",
                "New York State Assembly (48th District) \n District Office: 1310 48th Street, Brooklyn, NY 11219"
        ));

        list.add(new Politician(
                "Jamaal Bowman",
                "Democratic",
                default_image,
                "DC Office Phone: (202) 225-2464 \n District Office: (718) 518-5555",
                "Jamaal Bowman (born 1976) is the U.S. Representative for New York's 16th congressional district, serving since 2021. He is a former middle school principal and a progressive voice in Congress. He is a member of 'The Squad'.",
                "U.S. Representative (16th District) \n D.C. Address: 507 Cannon HOB, Washington, D.C. 20515"
        ));

        list.add(new Politician(
                "Joseph Crowley",
                "Democratic",
                default_image,
                "Former DC Office: (202) 225-3965 (Historical) \n Current: [Consultancy/Public Affairs]",
                "Joseph Crowley (born 1962) is a former U.S. Representative, serving New York's 14th congressional district from 1999 to 2019. He served as the Chairman of the House Democratic Caucus before being defeated by Alexandria Ocasio-Cortez in the 2018 primary.",
                "New York (Former U.S. Congressman)"
        ));

        list.add(new Politician(
                "Grace Lee",
                "Democratic",
                default_image,
                "District Office Phone: (212) 312-1420 \n Albany Office: (518) 455-5290",
                "Grace Lee is a member of the New York State Assembly, representing the 65th District (Lower Manhattan) since 2023. She ran on a platform focused on quality of life issues, public safety, and environmental protection.",
                "New York State Assembly (65th District) \n District Office: 250 Broadway, Suite 2201, New York, NY 10007"
        ));

        list.add(new Politician(
                "Brian Kavanagh",
                "Democratic",
                default_image,
                "District Office Phone: (212) 681-1801 \n Albany Office: (518) 455-3641",
                "Brian Kavanagh (born 1966) is a member of the New York State Senate, representing the 27th District (Lower Manhattan/Brooklyn). He serves as the Chair of the Senate Housing, Construction and Community Development Committee.",
                "New York State Senate (27th District) \n District Office: 250 Broadway, Suite 2011, New York, NY 10007"
        ));

        list.add(new Politician(
                "George Washington",
                "Independent",
                default_image,
                "N/A (Historical)",
                "George Washington (1732–1799) was an American military officer, statesman, and Founding Father who served as the first President of the United States from 1789 to 1797. He led the Continental Army to victory in the American Revolutionary War.",
                "First President of the United States (1789–1797)"
        ));

        list.add(new Politician(
                "Thomas Jefferson",
                "Democratic-Republican",
                default_image,
                "N/A (Historical)",
                "Thomas Jefferson (1743–1826) was an American statesman, diplomat, lawyer, architect, and Founding Father who served as the third President of the United States (1801–1809). He was the principal author of the Declaration of Independence.",
                "Third President of the United States (1801–1809)"
        ));

        list.add(new Politician(
                "Abraham Lincoln",
                "Republican",
                default_image,
                "N/A (Historical)",
                "Abraham Lincoln (1809–1865) was an American statesman and lawyer who served as the 16th President of the United States (1861–1865). He led the nation through the American Civil War, preserving the Union and abolishing slavery.",
                "Sixteenth President of the United States (1861–1865)"
        ));

        list.add(new Politician(
                "Franklin D. Roosevelt",
                "Democratic",
                default_image,
                "N/A (Historical)",
                "Franklin Delano Roosevelt (1882–1945) served as the 32nd President of the United States (1933–1945). He is the only U.S. president to have served more than two terms. He led the U.S. through the Great Depression and World War II.",
                "Thirty-second President of the United States (1933–1945)"
        ));

        list.add(new Politician(
                "John F. Kennedy",
                "Democratic",
                default_image,
                "N/A (Historical)",
                "John Fitzgerald Kennedy (1917–1963) was an American politician who served as the 35th President of the United States (1961–1963). He navigated the Cold War, including the Cuban Missile Crisis, and inspired the nation with his calls for public service.",
                "Thirty-fifth President of the United States (1961–1963)"
        ));

        list.add(new Politician(
                "Ronald Reagan",
                "Republican",
                default_image,
                "N/A (Historical) \n Post-Presidential Foundation: (805) 522-8444",
                "Ronald Wilson Reagan (1911–2004) was an American politician who served as the 40th President of the United States (1981–1989). He previously served as the Governor of California (1967–1975) and was a famous Hollywood actor.",
                "Fortieth President of the United States (1981–1989)"
        ));

        list.add(new Politician(
                "Bill Clinton",
                "Democratic",
                default_image,
                "Clinton Foundation: (212) 348-8830 \n Post-Presidential Office: [Varies]",
                "William Jefferson Clinton (born 1946) is an American politician who served as the 42nd President of the United States (1993–2001). He presided over the longest period of peacetime economic expansion in American history.",
                "Forty-second President of the United States (1993–2001)"
        ));

        list.add(new Politician(
                "George W. Bush",
                "Republican",
                default_image,
                "George W. Bush Presidential Center: (214) 200-4300",
                "George Walker Bush (born 1946) is an American politician who served as the 43rd President of the United States (2001–2009). He led the country during the 9/11 attacks and the ensuing 'War on Terror.' He previously served as the 46th Governor of Texas.",
                "Forty-third President of the United States (2001–2009)"
        ));

        list.add(new Politician(
                "Joe Biden",
                "Democratic",
                default_image,
                "The White House: (202) 456-1111",
                "Joseph Robinette Biden Jr. (born 1942) is an American politician who served as the 46th President of the United States (2021–2025). He previously served as the 47th Vice President of the United States and a long-time U.S. Senator from Delaware.",
                "Forty-sixth President of the United States (2021–2025)"
        ));

        list.add(new Politician(
                "Theodore Roosevelt",
                "Republican",
                default_image,
                "N/A (Historical)",
                "Theodore Roosevelt Jr. (1858–1919) was an American statesman, conservationist, and soldier who served as the 26th President of the United States (1901–1909). Known for his 'Square Deal' domestic policies and the 'Big Stick' foreign policy, he was also the 33rd Governor of New York.",
                "Twenty-sixth President of the United States (1901–1909)"
        ));

        list.add(new Politician(
                "Richard Nixon",
                "Republican",
                default_image,
                "N/A (Historical)",
                "Richard Milhous Nixon (1913–1994) was an American politician who served as the 37th President of the United States (1969–1974). He is known for establishing diplomatic relations with China and for the Watergate scandal, which led to his resignation.",
                "Thirty-seventh President of the United States (1969–1974)"
        ));

        list.add(new Politician(
                "Barack Obama",
                "Democratic",
                default_image,
                "[Contact information typically through the Obama Foundation or Post-Presidential Office]",
                "Barack Hussein Obama II (born 1961) is an American politician who served as the 44th President of the United States from 2009 to 2017. He was the first African American President of the United States. He previously served as a U.S. Senator for Illinois.",
                "Forty-fourth President of the United States (2009–2017)"
        ));


        return list;
    }

    public static List<User> getUsers()
    {
        List<User> list = new ArrayList<>();
        list.add(new User("[name]", "[email]", "[password]", "[location]", "[preferences]"));
        return list;
    }


}
